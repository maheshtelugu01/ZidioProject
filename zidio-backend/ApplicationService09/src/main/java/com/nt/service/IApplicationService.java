package com.nt.service;

import java.util.List;

import com.nt.dto.InternshipApplicationResponse;
import com.nt.dto.InternshipResponse;
import com.nt.dto.JobApplicationResponse;
import com.nt.dto.JobResponse;

public interface IApplicationService {
	public String applyJob(Long jobId);
	public String acceptJob(Long id);
	public String rejectJob(Long id);
	public List<JobResponse> getJobsByAppliedOrNot();
	public List<JobApplicationResponse> getJobApplicationsByStudent();
	public List<JobApplicationResponse> getJobApplicationsByJob(Long jobId);	
	public List<JobApplicationResponse> getAllJobApplications();
	public List<JobApplicationResponse> getAllJobApplicationByIds();
	public Long jobApplicationsCount();
	public String deactivateJobApplication(Long id);
	
	public String applyInternship(Long internshipId);
	public String acceptInternship(Long id);
	public String rejectInternship(Long id);
	public List<InternshipResponse> getInternshipsByAppliedOrNot();
	public List<InternshipApplicationResponse> getInternApplicationsByStudent();
	public List<InternshipApplicationResponse> getInternApplicationsByJob(Long internshipId);
	public List<InternshipApplicationResponse> getAllInternshipApplications();
	public List<InternshipApplicationResponse> getAllInternshipApplicationsByIds();
	public Long internshipApplicationsCount();
	Long countInternshipApplicationsByUser();
	Long countjobApplicationsByUser();
	String deactivateInternshipApplication(Long id);

}
