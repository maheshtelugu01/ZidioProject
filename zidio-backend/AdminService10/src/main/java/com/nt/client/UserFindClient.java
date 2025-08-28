package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nt.dto.UserStatusUpdateRequest;
import com.nt.entity.User;

@FeignClient("AUTH-SERVICE")
public interface UserFindClient {	
	@GetMapping("/api/auth/all")
	public List<User>getAllUsers();
	@PutMapping("/api/auth/deactivate")
	public String deactivateUser(@RequestBody UserStatusUpdateRequest  req);
	@PutMapping("/api/auth/activate")
	public String activateUser(@RequestBody UserStatusUpdateRequest  req);
}
