package com.nt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("NOTIFICATION-SERVICE")
public interface NotificationFindClient {
	@PutMapping("/api/notification/job/{message}")
	public String jobNotification(@PathVariable String message);
}
