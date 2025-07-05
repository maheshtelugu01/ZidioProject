package com.zidio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.dto.UserInfoResponse;
import com.zidio.dto.UserStatusUpdateRequest;
import com.zidio.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PutMapping("/user/status")
	public ResponseEntity<UserInfoResponse> updateUserStatus(@RequestBody UserStatusUpdateRequest req) {
		return ResponseEntity.ok(adminService.updateUserStatus(req));
	}
	@GetMapping("/users")
	public ResponseEntity<List<UserInfoResponse>> getAllUsers(){
		return ResponseEntity.ok(adminService.getAllUsers());
	}
	@GetMapping("/users/{role}")
	public ResponseEntity<List<UserInfoResponse>> getUserByRole(@PathVariable String role){
		return ResponseEntity.ok(adminService.getUsersByRole(role));
	} 
}
