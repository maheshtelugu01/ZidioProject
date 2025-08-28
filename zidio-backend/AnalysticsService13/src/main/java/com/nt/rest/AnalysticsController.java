package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.AnalysticsResponse;
import com.nt.service.AnalysticService;

@RequestMapping("/api/analystics")
@RestController
public class AnalysticsController {
	@Autowired
	private AnalysticService analysticsService;
	@GetMapping("/summary")
	public ResponseEntity<AnalysticsResponse>getSummary(){
		return ResponseEntity.ok(analysticsService.collectData());
	}
}
