package com.nt.dto;

import lombok.Data;

@Data
public class InternshipApplicationResponse {
	private Long id;
	private String studentEmail;
	private long internshipId;
	private String status;
}	
