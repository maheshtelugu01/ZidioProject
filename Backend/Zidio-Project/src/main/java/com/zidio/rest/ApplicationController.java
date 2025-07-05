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

import com.zidio.dto.ApplicationRequest;
import com.zidio.dto.ApplicationResponse;
import com.zidio.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/apply")
	public ResponseEntity<ApplicationResponse> apply(@RequestBody ApplicationRequest req){
		return ResponseEntity.ok(applicationService.apply(req));
	}
	@GetMapping("/student/{email}")
	public ResponseEntity<List<ApplicationResponse>>getByStudentEmail(@PathVariable String studentEmail){
		return ResponseEntity.ok(applicationService.getApplicationsByStudent(studentEmail));
	}
	@GetMapping("/job/{jobId}")
	public ResponseEntity<List<ApplicationResponse>>getByJobId(@PathVariable Long jobId){
		return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
	}
	
}
