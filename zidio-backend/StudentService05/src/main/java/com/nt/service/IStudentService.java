package com.nt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nt.dto.StudentDto;

public interface IStudentService {
	public String saveStudent(StudentDto dto);

	public StudentDto getStudent(String email);

	public List<StudentDto> findAll();

	public Long studentCount();
	public String updateStudentResume(MultipartFile file)throws IOException;
	public List<String>findAllEmails();
}
