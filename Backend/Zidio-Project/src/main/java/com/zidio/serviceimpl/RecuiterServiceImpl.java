package com.zidio.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.JobDto;
import com.zidio.dto.RecuiterDto;
import com.zidio.entity.Job;
import com.zidio.entity.Recuiter;
import com.zidio.repository.JobRepository;
import com.zidio.repository.RecuiterRepository;
import com.zidio.service.RecuiterService;

@Service
public class RecuiterServiceImpl implements RecuiterService{
	@Autowired
	private RecuiterRepository recuiterRepo;
	@Autowired
	private JobRepository jobRepo;
	@Override
	public RecuiterDto saveProfile(RecuiterDto dto) {
		Recuiter recuiter=new Recuiter();
		recuiter.setCompanyName(dto.getCompanyName());
		recuiter.setRecuiterEmail(dto.getRecruiterEmail());
		recuiter.setRecuiterName(dto.getRecuiterName());
		recuiter.setDesignation(dto.getDesignation());
		return toRecuiterDto(recuiterRepo.save(recuiter));
	}

	@Override
	public JobDto postJob(JobDto dto) {
		Job job=new Job();
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setCompanyName(dto.getCompanyName());
		job.setLocation(dto.getLocation());
		job.setJobType(dto.getJobType());
		job.setDuration(dto.getDuration());
		job.setSkills(dto.getSkills());
		return toJobDto(jobRepo.save(job));
	}

//	@Override
//	public List<JobDto> getPostedJob(String recuiterEmail) {
//		return jobRepo.findByPostedBy(recuiterEmail).stream().map(this::toJobDto).collect(Collectors.toList());
//	}

	@Override
	public List<JobDto> getAllJobs() {
		return jobRepo.findAll().stream().map(this::toJobDto).collect(Collectors.toList());
	}


	@Override
	public RecuiterDto getByRecuiterEmail(String recuiterEmail) {
		Recuiter recuiter=recuiterRepo.findByRecuiterEmail(recuiterEmail).get();
		if(recuiter==null){
			throw new RuntimeException("Recuiter not found");
		}
		return toRecuiterDto(recuiter);
	}
	private RecuiterDto toRecuiterDto(Recuiter recuiter) {
		RecuiterDto dto=new RecuiterDto();
		dto.setRid(recuiter.getRid());
		dto.setRecuiterName(recuiter.getRecuiterName());
		dto.setRecruiterEmail(recuiter.getRecuiterEmail());
		dto.setCompanyName(recuiter.getCompanyName());
		dto.setDesignation(recuiter.getDesignation());
		return dto;
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
