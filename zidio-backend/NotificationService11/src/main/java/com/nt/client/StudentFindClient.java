package com.nt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("STUDENT-SERVICE")
public interface StudentFindClient {
	@GetMapping("/api/student/email/findAll")
	public List<String> allStudentsEmails();
}
