package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("AUTH-SERVICE")
public interface UserFindClient {
	@GetMapping("/api/auth/user")
	public String findUserEmail();
	@GetMapping("/api/auth/email/findAll")
	public List<String> allUsersEmails();
	
}
