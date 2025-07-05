package com.zidio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {
	private Long aid;
	private String studentEmail;
	private long jobId;
	private String status;
}	
