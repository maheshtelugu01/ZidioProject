package com.nt.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nt.client.SubscriptionFindClient;
import com.nt.client.UserFindClient;
import com.nt.dto.NotificationDto;
import com.nt.dto.SubscriptionDto;
import com.nt.dto.SubscriptionStatusDto;
import com.nt.dto.UserSubscriptionStatusDto;
import com.nt.entity.UserSubscriptionStatus;
import com.nt.enums.SubscriptionStatus;
import com.nt.repository.UserSubscriptionStatusRepository;
import com.nt.service.IUserSubscriptionStatusService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSubscriptionStatusServiceImpl implements IUserSubscriptionStatusService {
	@Autowired
	private UserSubscriptionStatusRepository statusRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserFindClient userClient;
	@Autowired
	private JmsTemplate template;
	@Autowired
	private SubscriptionFindClient planClient;

	@Override
	public String activateSubscription(Long planId) {
		String email=userClient.findUserEmail();
		SubscriptionDto dto=planClient.findSubscription(planId);
		Optional<UserSubscriptionStatus> sub=statusRepo.findByEmailAndStatus(email,SubscriptionStatus.ACTIVE);
		if(sub.isPresent()) {
		UserSubscriptionStatus sub1=sub.get();
		sub1.setStatus(SubscriptionStatus.EXPIRED);
		sub1.setSubscriptionEnd(LocalDateTime.now());
		statusRepo.save(sub1);
		}
			UserSubscriptionStatus status=new UserSubscriptionStatus();
			status.setEmail(email);
			status.setPlanId(dto.getId());
			status.setPlanName(dto.getName());
			status.setStatus(SubscriptionStatus.ACTIVE);
			status.setSubscriptionStart(LocalDateTime.now());
			status.setSubscriptionEnd(LocalDateTime.now().plusDays(dto.getDurationDays()));
			status.setMaxApplications(dto.getMaxApplications());
			status.setUsedApplications(0);
		
		statusRepo.save(status);
		template.convertAndSend("notification-topic",new NotificationDto(email,"Your Subscription has '"+status.getStatus()+"' Updated..","USERSUBSCRIPTIONSTATUS-SERVICE"));
		return "your Subscription Updated as Active";
	}
	@Override
	public String incrementUserApplications() {
		String email=userClient.findUserEmail();
		UserSubscriptionStatus activeSub=statusRepo.findByEmailAndStatus(email,SubscriptionStatus.ACTIVE).orElseThrow(()->new RuntimeException("No Active Subscription"));
		if(activeSub.getSubscriptionEnd().isBefore(LocalDateTime.now())) {
			activeSub.setStatus(SubscriptionStatus.EXPIRED);
			statusRepo.save(activeSub);
			template.convertAndSend("notification-topic",new NotificationDto(email,"Your Subscription has Expired..Please Upgrade!!","USERSUBSCRIPTIONSTATUS-SERVICE"));
			throw new RuntimeException("Subscrption Expired...");
		}
		int maxApplications=activeSub.getMaxApplications();
		if(activeSub.getUsedApplications()>=maxApplications) {
			template.convertAndSend("notification-topic",new NotificationDto(email,"Your Max Applications Reached..Please Upgrade your Plan","USERSUBSCRIPTIONSTATUS-SERVICE"));
			throw new RuntimeException("Maximum applications Reached!!! Please Upgrade Your Plan");
			
		}
		activeSub.setUsedApplications(activeSub.getUsedApplications()+1);
		statusRepo.save(activeSub);
		return "Your Application Accepted";
	}
	@Override
	public UserSubscriptionStatusDto getActiveSubscriptionByEmail() {
		String email=userClient.findUserEmail();
		LocalDateTime now=LocalDateTime.now();
		return mapper.map(statusRepo.findByEmailAndSubscriptionStartBeforeAndSubscriptionEndAfter(email, now, now),UserSubscriptionStatusDto.class);
	}
	@Override
	public boolean checkUserStatus() {
		String email=userClient.findUserEmail();
		return statusRepo.findByEmailAndStatus(email,SubscriptionStatus.ACTIVE).isPresent();
	}
	@Override
	public List<SubscriptionStatusDto> allAvailableSubscriptions() {
		String email=userClient.findUserEmail();
		List<SubscriptionDto> allSubscriptions=planClient.findAllSubscription();
		if(getActiveSubscriptionByEmail()==null) {
			return allSubscriptions.stream().map(sub->new SubscriptionStatusDto(sub.getId(),sub.getName(),sub.getPrice(),
					sub.getDescription(),sub.getDurationDays(),sub.getMaxApplications(),
					"SUBSCRIBE")).toList();
		}
		UserSubscriptionStatusDto dto=getActiveSubscriptionByEmail();
		List<UserSubscriptionStatus> subscribed=statusRepo.findByEmail(email);
		Set<Long>subscribedIds=subscribed.stream().map(UserSubscriptionStatus::getPlanId).collect(Collectors.toSet());
		return allSubscriptions.stream().map(sub->new SubscriptionStatusDto(sub.getId(),sub.getName(),sub.getPrice(),
				sub.getDescription(),sub.getDurationDays(),sub.getMaxApplications(),
				(dto.getPlanId().equals(sub.getId())?"ACTIVE":subscribedIds.contains(sub.getId())?"EXPIRED":"NOTSUBSCRIBED"))).toList();
	}

}
