package com.nt.dto;

import lombok.Data;

@Data
public class RecuiterDto {
	private Long id;
	private String companyName;
	private String recuiterEmail;
	private String recuiterName;
	private String designation;	
	private String linkedInUrl;

}
