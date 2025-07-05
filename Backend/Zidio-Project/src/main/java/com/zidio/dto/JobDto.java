package com.zidio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
	private Long jid;
	private String title;
	private String description;
	private String companyName;
	private String location;
	private String jobType;
	private String duration;
	private String skills;
}
