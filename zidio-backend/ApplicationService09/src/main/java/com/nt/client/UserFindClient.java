package com.nt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("AUTH-SERVICE")
public interface UserFindClient {
	@GetMapping("/api/auth/user")
	public String findUserEmail();
}
