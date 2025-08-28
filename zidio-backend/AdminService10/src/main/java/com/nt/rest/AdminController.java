package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.UserStatusUpdateRequest;
import com.nt.entity.User;
import com.nt.service.IAdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;

	@PutMapping("/activate")
	public ResponseEntity<String> activateUser(@RequestBody UserStatusUpdateRequest req) {
		return ResponseEntity.ok(adminService.activateUser(req));
	}

	@PutMapping("/deactivate")
	public ResponseEntity<String> deactivateUser(@RequestBody UserStatusUpdateRequest req) {
		return ResponseEntity.ok(adminService.deactivateUser(req));
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(adminService.getAllUsers());
	}
}
