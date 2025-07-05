package com.zidio.service;

import java.util.List;

import com.zidio.dto.JobDto;

public interface JobService {
	public String jobPost(JobDto dto);
	public List<JobDto> getAllJobs();
	public List<JobDto> searchByTitle(String title);
	public List<JobDto> searchByCompanyName(String companyName);
}
