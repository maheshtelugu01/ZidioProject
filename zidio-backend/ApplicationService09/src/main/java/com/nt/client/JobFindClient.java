package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.nt.dto.JobDto;
import com.nt.dto.JobResponse;

@FeignClient("JOB-SERVICE")
public interface JobFindClient {
	@GetMapping("/api/job/yourJobIds")
	public List<Long> getJobIdsByRecuiter();
	@GetMapping("/api/job/findAll")
	public List<JobDto> getAllJobs();
}
