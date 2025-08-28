package com.nt.dto;

import lombok.Data;

@Data
public class JobApplicationResponse {
	private Long id;
	private String studentEmail;
	private long jobId;
	private String status;
}	
