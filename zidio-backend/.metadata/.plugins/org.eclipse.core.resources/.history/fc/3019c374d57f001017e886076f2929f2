package com.nt.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nt.client.NotificationFindClient;
import com.nt.client.UserFindClient;
import com.nt.client.UserSubscriptionStatusFindClient;
import com.nt.dto.InternshipDto;
import com.nt.dto.NotificationDto;
import com.nt.entity.Internship;
import com.nt.entity.User;
import com.nt.repository.InternshipRepository;
import com.nt.service.IInternshipService;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InternshipServiceImpl implements IInternshipService {

	@Autowired
	private JmsTemplate template;
	@Autowired
	private InternshipRepository internshipRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserFindClient userClient;
	@Autowired
	private UserSubscriptionStatusFindClient subClient;

     

	@Override
	public List<InternshipDto> getAllInternships() {
		log.info("getAllInternships(-)-->InternshipService");
		return internshipRepo.findAll().stream().map(internship -> mapper.map(internship, InternshipDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<InternshipDto> searchByTitle(String title) {
		log.info("searchByTitle(-)-->InternshipService");
		return internshipRepo.findByTitle(title).stream().map(internship -> mapper.map(internship, InternshipDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<InternshipDto> searchByCompanyName(String companyName) {
		log.info("searchByCompanyName(-)-->InternshipService");
		return internshipRepo.findByCompanyName(companyName).stream()
				.map(internship -> mapper.map(internship, InternshipDto.class)).collect(Collectors.toList());
	}

	@Override
	public String postInternships(InternshipDto dto) {
		log.info("postInternships(-)-->InternshipService");
		User user = userClient.findUser();
		Long count=countByUserEmail();
		if(!subClient.activeSubscription()) {
			if(count>=10) {
				template.convertAndSend("notification-topic",new NotificationDto(user.getEmail(),"Free INTERNSHIP Applications limit is reachend U have no Active Plan.. Please Upgrade!","INTERNSHIP-SERVICE"));
			throw new RuntimeException("Free INTERNSHIP Applications limit is reachend U have no Active Plan.. Please Upgrade!");
			}
		}

		Internship intern = new Internship();
		intern.setTitle(dto.getTitle());
		intern.setDescription(dto.getDescription());
		intern.setCompanyName(dto.getCompanyName());
		intern.setLocation(dto.getLocation());
		intern.setStartDate(dto.getStartDate());
		intern.setDurationWeeks(dto.getDurationWeeks());
		intern.setStipend(dto.getStipend());
		intern.setPostedBy(user);
		subClient.incrementApplicationCount();
		internshipRepo.save(intern);
		template.convertAndSend("notification-topic",new NotificationDto("all-students","Alert New INTERNSHIP ** Posted..","INTERNSHIP-SERVICE"));
		return "Internship Posted Successfully...";
	}

	@Override
	public List<InternshipDto> getPostedInternships(String recuiterEmail) {
		return internshipRepo.findInternshipsByRecuiterEmail(recuiterEmail).stream()
				.map(internship -> mapper.map(internship, InternshipDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<InternshipDto> yourInternships() {
		User user=userClient.findUser();
		return internshipRepo.findInternshipsByRecuiterEmail(user.getEmail()).stream()
				.map(internship -> mapper.map(internship, InternshipDto.class)).collect(Collectors.toList());

	}
	@Override
	public List<Long> yourInternshipIds() {
		User user=userClient.findUser();
		return internshipRepo.findInternshipIdsByRecuiterEmail(user.getEmail());

	}

	@Override
	public Long internshipCount() {
		return internshipRepo.count();
	}
	public Long countByUserEmail() {
		User user=userClient.findUser();
		return internshipRepo.countInternshipsByUserEmail(user.getEmail());
	}
	@Override
	public String deactivateInternship(Long id) {
		Internship internship=internshipRepo.findById(id).orElseThrow(()->new RuntimeException("Internship Not found By Id"));
		internshipRepo.delete(internship);
		return "Internship Deactivated";
	}
	 
}
