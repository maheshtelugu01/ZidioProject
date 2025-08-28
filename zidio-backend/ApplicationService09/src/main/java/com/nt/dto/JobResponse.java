package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobResponse {
	private Long id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private String jobType;
	private String duration;
	private String skills;
	private String status;
}
