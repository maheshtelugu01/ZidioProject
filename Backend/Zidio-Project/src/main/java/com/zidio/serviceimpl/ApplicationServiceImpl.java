package com.zidio.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.ApplicationRequest;
import com.zidio.dto.ApplicationResponse;
import com.zidio.entity.Application;
import com.zidio.repository.ApplicationRepository;
import com.zidio.service.ApplicationService;
@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepo;
	@Override
	public ApplicationResponse apply(ApplicationRequest req) {
		applicationRepo.findByStudentEmailAndJobId(req.getStudentEmail(), req.getJobId()).ifPresent(a->{throw new RuntimeException("Already Applied");});
		//Application app=Application.builder().studentEmail(req.studentEmail).jobId(req.jobId).stauts("applied").build();
		Application app=new Application();
		app.setStudentEmail(req.getStudentEmail());
		app.setJobId(req.getJobId());
		app.setStatus("applied");
		applicationRepo.save(app);
		return mapToResponse(app);
		
	}

	@Override
	public List<ApplicationResponse> getApplicationsByStudent(String studentEmail) {
		return applicationRepo.findByStudentEmail(studentEmail).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public List<ApplicationResponse> getApplicationsByJob(Long jobId) {
		return applicationRepo.findByJobId(jobId).stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	private ApplicationResponse mapToResponse(Application app) {
		ApplicationResponse response=new ApplicationResponse();
		response.setAid(app.getAid());
		response.setStudentEmail(app.getStudentEmail());		
		response.setJobId(app.getJobId());		
		response.setStatus(app.getStatus());		
	
		return response;
	}

}
