package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.SubscriptionDto;
import com.nt.service.ISubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
	@Autowired
	private ISubscriptionService subService;

	@GetMapping("/plans")
	public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
		return ResponseEntity.ok(subService.getAllSubscriptions());
	}

	@PostMapping("/save")
	public ResponseEntity<String> createSubscription(@RequestBody SubscriptionDto dto) {
		return ResponseEntity.ok(subService.createSubscription(dto));
	}
	
	@GetMapping("/plan/{id}")
	public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable Long id) {
		return ResponseEntity.ok(subService.getSubscriptionById(id));
	}

}
