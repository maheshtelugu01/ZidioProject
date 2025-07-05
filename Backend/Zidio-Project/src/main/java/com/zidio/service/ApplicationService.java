package com.zidio.service;

import java.util.List;

import com.zidio.dto.ApplicationRequest;
import com.zidio.dto.ApplicationResponse;

public interface ApplicationService {
	public ApplicationResponse apply(ApplicationRequest req);
	public List<ApplicationResponse> getApplicationsByStudent(String studentEmail);
	public List<ApplicationResponse> getApplicationsByJob(Long jobId);	
}
