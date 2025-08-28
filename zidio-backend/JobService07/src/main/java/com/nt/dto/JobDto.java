package com.nt.dto;

import lombok.Data;

@Data
public class JobDto {
	private Long id;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private String jobType;
	private String duration;
	private String skills;
}
