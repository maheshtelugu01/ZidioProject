package com.zidio.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
	private Long sid;
	private String name;
	private String email;
	private String course;
	private String university;
	private String resumeUrl;
}
