package com.zidio.service;

import java.util.List;

import com.zidio.dto.JobDto;
import com.zidio.dto.RecuiterDto;

public interface RecuiterService {
	public RecuiterDto saveProfile(RecuiterDto dto);
	public RecuiterDto getByRecuiterEmail(String recuiterEmail);
	public JobDto postJob(JobDto dto);
	//public List<JobDto>getPostedJob(String recuiterEmail);
	public List<JobDto>getAllJobs();
}
