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
import com.zidio.dto.RecuiterDto;
import com.zidio.service.RecuiterService;

@RestController
@RequestMapping("/api/recuiter")
public class RecuiterController  {
	@Autowired
	private RecuiterService recuiterService;
	
	@PostMapping("/profile")
	public ResponseEntity<RecuiterDto>saveProfile(@RequestBody RecuiterDto dto){
		return ResponseEntity.ok(recuiterService.saveProfile(dto));
	}
	@PostMapping("/job")
	public ResponseEntity<JobDto>postJobs(@RequestBody JobDto dto){
		return ResponseEntity.ok(recuiterService.postJob(dto));
	}
//	@GetMapping("/jobs/{email}")
//	public ResponseEntity<List<JobDto>>getPostedJob(@PathVariable String email){
//		return ResponseEntity.ok(recuiterService.getPostedJob(email));
//	}
	@GetMapping("/jobs")
	public ResponseEntity<List<JobDto>>getAllJobs(){
		return ResponseEntity.ok(recuiterService.getAllJobs());
	}
	@GetMapping("{recuiterEmail}")
	public ResponseEntity<RecuiterDto>getByRecuiterEmail(@PathVariable String recuiterEmail){
		return ResponseEntity.ok(recuiterService.getByRecuiterEmail(recuiterEmail));
	}
	
 
}
