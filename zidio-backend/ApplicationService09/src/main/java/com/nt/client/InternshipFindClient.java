package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.nt.dto.InternshipDto;

@FeignClient("INTERNSHIP-SERVICE")
public interface InternshipFindClient {
	@GetMapping("/api/internship/yourInternshipIds")
	public List<Long> getInternshipIdsByRecuiter();
	@GetMapping("/api/internship/findAll")
	public List<InternshipDto> getAllInternships();
}
