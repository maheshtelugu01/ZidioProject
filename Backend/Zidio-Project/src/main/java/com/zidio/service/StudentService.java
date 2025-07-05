package com.zidio.service;

import com.zidio.dto.StudentDto;


public interface StudentService {
	public String createOrUpdate(StudentDto dto);
	public StudentDto getProfile(String email);
}
