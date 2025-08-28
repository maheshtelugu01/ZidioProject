package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.EmailRequest;
import com.nt.dto.NotificationDto;
import com.nt.entity.Notification;
import com.nt.service.INotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	@Autowired
	private INotificationService notifyService;
	@PostMapping("/email/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest req){
		return ResponseEntity.ok(notifyService.sendEmail(req));
	}	
	@GetMapping("/findAll")
	public ResponseEntity<List<NotificationDto>>getAllNotifications(){
		return ResponseEntity.ok(notifyService.getAllNotifications());
	}
}
