package com.zidio.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.JobDto;
import com.zidio.entity.Job;
import com.zidio.repository.JobRepository;
import com.zidio.service.JobService;
@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepo;
	
	@Override
	public String jobPost(JobDto dto) {
		Job job=new Job();
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setCompanyName(dto.getCompanyName());
		job.setLocation(dto.getLocation());
		job.setJobType(dto.getJobType());
		job.setDuration(dto.getDuration());
		job.setSkills(dto.getSkills());
		jobRepo.save(job);
		return "Job post got saved";
	}

	@Override
	public List<JobDto> getAllJobs() {
		return jobRepo.findAll().stream().map(this::toJobDto).collect(Collectors.toList());
	}

	@Override
	public List<JobDto> searchByTitle(String title) {
		return jobRepo.findByTitle(title).stream().map(this::toJobDto).collect(Collectors.toList());
	}

	@Override
	public List<JobDto> searchByCompanyName(String companyName) {
		return jobRepo.findByCompanyName(companyName).stream().map(this::toJobDto).collect(Collectors.toList());
	}
	private JobDto toJobDto(Job job) {
		JobDto dto=new JobDto();
		dto.setJid(job.getJid());
		dto.setTitle(job.getTitle());
		dto.setDescription(job.getDescription());
		dto.setCompanyName(job.getCompanyName());
		dto.setLocation(job.getLocation());
		dto.setJobType(job.getJobType());
		dto.setDuration(job.getDuration());
		dto.setSkills(job.getSkills());
		return dto;
	}
	
}
