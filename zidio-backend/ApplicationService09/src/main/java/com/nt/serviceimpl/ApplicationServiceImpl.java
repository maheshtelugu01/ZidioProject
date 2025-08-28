package com.nt.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nt.client.InternshipFindClient;
import com.nt.client.JobFindClient;
import com.nt.client.UserFindClient;
import com.nt.client.UserSubscriptionStatusFindClient;
import com.nt.dto.InternshipApplicationResponse;
import com.nt.dto.InternshipDto;
import com.nt.dto.InternshipResponse;
import com.nt.dto.JobApplicationResponse;
import com.nt.dto.JobDto;
import com.nt.dto.JobResponse;
import com.nt.dto.NotificationDto;
import com.nt.entity.InternshipApplication;
import com.nt.entity.JobApplication;
import com.nt.repository.InternshipApplicationRepository;
import com.nt.repository.JobApplicationRepository;
import com.nt.service.IApplicationService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ApplicationServiceImpl implements IApplicationService {
	@Autowired
	private JmsTemplate template;
	@Autowired
	private JobApplicationRepository jobapplicationRepo;
	@Autowired
	private InternshipApplicationRepository internapplicationRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JobFindClient jobClient;
	@Autowired
	private UserFindClient userClient;
	@Autowired
	private InternshipFindClient internshipClient;
	@Autowired
	private UserSubscriptionStatusFindClient subClient;
	@Override
	public String applyJob(Long jobId) {
		log.info("apply(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		long count=countjobApplicationsByUser();
		if(!subClient.activeSubscription()) {
			if(count>=10) {
			throw new RuntimeException("Free Applications limit is reachend U have no Active Plan.. Please Upgrade!");
			}
		}
		jobapplicationRepo.findByStudentEmailAndJobId(email,jobId).ifPresent(a->{throw new RuntimeException("Already Applied");});
		//Application app=Application.builder().studentEmail(req.studentEmail).jobId(req.jobId).stauts("applied").build();
		JobApplication app=new JobApplication();
		app.setStudentEmail(email);
		app.setJobId(jobId);
		subClient.incrementApplicationCount();
		jobapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your JOB Application is APPLIED..","APPLICATION-SERVICE"));
		return "JOB APPLIED SUCCESSFULLY";
		
	}
	@Override
	public String acceptJob(Long id) {
		JobApplication app= jobapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("Application not found"));
		app.setStatus("ACCEPTED");
		jobapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your Job Application is ACCEPTED..","RECUITER-SERVICE"));
		return "Application Accepted";
	}
	@Override
	public String rejectJob(Long id) {
		JobApplication app= jobapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("Application not found"));
		app.setStatus("REJECTED");
		jobapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your JOB Application is REJECTED..","RECUITER-SERVICE"));
		return "Application Rejected";
	}
	@Override
	public List<JobApplicationResponse> getJobApplicationsByStudent() {
		log.info("getJobApplicationsByStudent(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		return jobapplicationRepo.findByStudentEmail(email).stream().map(jobapp->mapper.map(jobapp,JobApplicationResponse.class)).collect(Collectors.toList());
	}

	@Override
	public List<JobApplicationResponse> getJobApplicationsByJob(Long jobId) {
		log.info("getJobApplicationsByJob(-)-->ApplicationService");
		return jobapplicationRepo.findByJobId(jobId).stream().map(jobapp->mapper.map(jobapp,JobApplicationResponse.class)).collect(Collectors.toList());
	}

	@Override
	public String applyInternship(Long internshipId) {
		log.info("apply(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		long count=countInternshipApplicationsByUser();
		if(!subClient.activeSubscription()) {
			if(count>=10) {
			throw new RuntimeException("Free Applications limit is reachend U have no Active Plan.. Please Upgrade!");
			}
		}
		internapplicationRepo.findByStudentEmailAndInternshipId(email, internshipId).ifPresent(a->{throw new RuntimeException("Already Applied");});
		//Application app=Application.builder().studentEmail(req.studentEmail).jobId(req.jobId).stauts("applied").build();
		InternshipApplication app=new InternshipApplication();
		app.setStudentEmail(email);
		app.setInternshipId(internshipId);
		subClient.incrementApplicationCount();
		internapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your INTERNSHIP Application is APPLIED..","APPLICATION-SERVICE"));
		return "INTERNSHIP APPLIED SUCCESSFULLY";
	}
	@Override
	public String acceptInternship(Long id) {
		InternshipApplication app= internapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("Application not found"));
		app.setStatus("ACCEPTED");
		internapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your INTERNSHIP Application is ACCEPTED..","RECUITER-SERVICE"));

		return "Application ACCEPTED";
	}
	@Override
	public String rejectInternship(Long id) {
		InternshipApplication app= internapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("Application not found"));
		app.setStatus("REJECTED");
		internapplicationRepo.save(app);
		template.convertAndSend("notification-topic",new NotificationDto(app.getStudentEmail(),"Your INTERNSHIP Application is REJECTED..","JOB-SERVICE"));

		return "Application REJECTED";
	}
	@Override
	public List<InternshipApplicationResponse> getInternApplicationsByStudent() {
		log.info("getInternApplicationsByStudent(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		return internapplicationRepo.findByStudentEmail(email).stream().map(internapp->mapper.map(internapp,InternshipApplicationResponse.class)).collect(Collectors.toList());
	}

	@Override
	public List<InternshipApplicationResponse> getInternApplicationsByJob(Long internshipId) {
		log.info("getInternApplicationsByJob(-)-->ApplicationService");
		
		return internapplicationRepo.findByInternshipId(internshipId).stream().map(internapp->mapper.map(internapp,InternshipApplicationResponse.class)).collect(Collectors.toList());
	}

	@Override
	public List<JobApplicationResponse> getAllJobApplications() {
		log.info("getAllJobApplications(-)-->ApplicationService");
		return jobapplicationRepo.findAll().stream().map(jobapp->mapper.map(jobapp,JobApplicationResponse.class)).collect(Collectors.toList());
	}

	@Override
	public List<InternshipApplicationResponse> getAllInternshipApplications() {
		log.info("getAllInternshipApplications(-)-->ApplicationService");
		return internapplicationRepo.findAll().stream().map(internapp->mapper.map(internapp,InternshipApplicationResponse.class)).collect(Collectors.toList());
	}
	@Override
	public List<JobApplicationResponse> getAllJobApplicationByIds() {
		log.info("getAllJobApplicationsByIds(-)-->ApplicationService");
		List<Long> jobIds=jobClient.getJobIdsByRecuiter();
		return jobapplicationRepo.findByJobIdIn(jobIds).stream().map(jobapp->mapper.map(jobapp,JobApplicationResponse.class)).collect(Collectors.toList());

	}
	@Override
	public List<InternshipApplicationResponse> getAllInternshipApplicationsByIds() {
		log.info("getAllInternshipApplicationsByIds(-)-->ApplicationService");
		List<Long> internshipIds=internshipClient.getInternshipIdsByRecuiter();
		return internapplicationRepo.findByInternshipIdIn(internshipIds).stream().map(internapp->mapper.map(internapp,InternshipApplicationResponse.class)).collect(Collectors.toList());
	}
	@Override
	public Long internshipApplicationsCount() {
		log.info("internshipApplicationsCount(-)-->ApplicationService");
		return internapplicationRepo.count();
	}
	@Override
	public Long jobApplicationsCount() {
		log.info("jobApplicationsCount(-)-->ApplicationService");
		return jobapplicationRepo.count();
	}
	@Override
	public List<JobResponse> getJobsByAppliedOrNot() {
		log.info("getJobsByAppliedOrNot(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		List<JobDto>allJobs=jobClient.getAllJobs();
		List<JobApplication>apps=jobapplicationRepo.findByStudentEmail(email);
		Set<Long>appliedJobIds=apps.stream().map(JobApplication::getJobId).collect(Collectors.toSet());
		return allJobs.stream().map(job->new JobResponse(job.getId(),job.getTitle(),job.getCompanyName()
				,job.getDescription(),job.getLocation(),job.getJobType(),job.getDuration(),job.getSkills(),appliedJobIds.contains(job.getId())?"APPLIED":"APPLY")).toList();
		
	}
	@Override
	public List<InternshipResponse> getInternshipsByAppliedOrNot() {
		log.info("getInternshipsByAppliedOrNot(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		List<InternshipDto>allInternships=internshipClient.getAllInternships();
		List<InternshipApplication>apps=internapplicationRepo.findByStudentEmail(email);
		Set<Long>appliedInternshipIds=apps.stream().map(InternshipApplication::getInternshipId).collect(Collectors.toSet());
		return allInternships.stream().map(job->new InternshipResponse(job.getId(),job.getTitle(),job.getDescription()
				,job.getLocation(),job.getCompanyName(),job.getStartDate(),job.getDurationWeeks(),job.getStipend(),appliedInternshipIds.contains(job.getId())?"APPLIED":"APPLY")).toList();
		
	}
	@Override
	public Long countjobApplicationsByUser() {
		log.info("countjobApplicationsByUser(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		return jobapplicationRepo.countByStudentEmail(email);
	}
	@Override
	public Long countInternshipApplicationsByUser() {
		log.info("countInternshipApplicationsByUser(-)-->ApplicationService");
		String email=userClient.findUserEmail();
		return internapplicationRepo.countByStudentEmail(email);
	}
	@Override
	public String deactivateInternshipApplication(Long id) {
		log.info("deactivateInternshipApplication(-)-->ApplicationService");
		InternshipApplication app=internapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("Internship Application Not found"));
		internapplicationRepo.delete(app);
		return "InternshipApplication Deactivated";
	}
	@Override
	public String deactivateJobApplication(Long id) {
		log.info("deactivateJobApplication(-)-->ApplicationService");
		JobApplication job=jobapplicationRepo.findById(id).orElseThrow(()->new RuntimeException("JobApplication Not Found"));
		jobapplicationRepo.delete(job);
		return "JobApplication Deativated";
	}
	 

}
