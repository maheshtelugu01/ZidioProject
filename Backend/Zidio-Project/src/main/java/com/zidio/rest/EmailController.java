package com.zidio.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.dto.EmailRequest;
import com.zidio.service.EmailService;

//@RestController
//RequestMapping("/api/notifications")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@PostMapping("/send")
	public ResponseEntity<String> send(@RequestBody EmailRequest req){
		return ResponseEntity.ok(emailService.sendEmail(req));
	}
}
