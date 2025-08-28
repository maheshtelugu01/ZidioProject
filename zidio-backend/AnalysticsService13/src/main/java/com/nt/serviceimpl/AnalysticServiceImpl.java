package com.nt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.dto.AnalysticsResponse;
import com.nt.service.AnalysticService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class AnalysticServiceImpl implements AnalysticService{
	@Autowired
	private RestTemplate template;
	
	@Override
	@CircuitBreaker(name="myService",fallbackMethod="fallbackResponse")
	public AnalysticsResponse collectData() {
		AnalysticsResponse response=new AnalysticsResponse();
		response.setTotalStudents(template.getForObject("http://STUDENT-SERVICE/api/student/count", Long.class));
		response.setTotalRecuiters(template.getForObject("http://RECUITER-SERVICE/api/recuiter/count", Long.class));
		response.setTotalJobApplications(template.getForObject("http://APPLICATION-SERVICE/api/application/job/count", Long.class));
		response.setTotalInternshipApplications(template.getForObject("http://APPLICATION-SERVICE/api/application/internship/count", Long.class));
		response.setTotalJobs(template.getForObject("http://JOB-SERVICE/api/job/count", Long.class));
		response.setTotalInternships(template.getForObject("http://INTERNSHIP-SERVICE/api/internship/count", Long.class));
		return response;
	}
	public AnalysticsResponse fallbackResponse(Throwable t) {
		AnalysticsResponse response=new AnalysticsResponse();
		response.setTotalStudents(0L);;
		response.setTotalRecuiters(0L);
		response.setTotalJobApplications(0L);
		response.setTotalInternshipApplications(0L);
		response.setTotalJobs(0L);
		response.setTotalInternships(0L);
		System.out.println("fallbackData due to some Service May Down...");
		return response;
	}
	
}
