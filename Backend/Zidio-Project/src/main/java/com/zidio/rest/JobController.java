package com.zidio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.dto.JobDto;
import com.zidio.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@PostMapping("/jobPost")
	public ResponseEntity<String>postJob(@RequestBody JobDto dto){
		return ResponseEntity.ok(jobService.jobPost(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<JobDto>>getAllJobs(){
		return ResponseEntity.ok(jobService.getAllJobs());
	}
	@GetMapping("/search/title/{title}")
	public ResponseEntity<List<JobDto>>searchByTitle(@PathVariable String title){
		return ResponseEntity.ok(jobService.searchByTitle(title));
		
	}
	@GetMapping("/search/companyName/{companyName}")
	public ResponseEntity<List<JobDto>>searchByCompanyName(@PathVariable String companyName){
		return ResponseEntity.ok(jobService.searchByCompanyName(companyName));
	}
}
