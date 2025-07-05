package com.zidio.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.StudentDto;
import com.zidio.entity.Student;
import com.zidio.repository.StudentRepository;
import com.zidio.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public String createOrUpdate(StudentDto dto) {
		//Student student=new Student();
		Student student=studentRepo.findByEmail(dto.getEmail()).orElse(new Student());
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setCourse(dto.getCourse());
		student.setUniversity(dto.getUniversity());
		student.setResumeUrl(dto.getResumeUrl());
		studentRepo.save(student);
		return "Student Profile got saved";
	}

	@Override
	public StudentDto getProfile(String email) {
		Student s=studentRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Student not Found"));
		StudentDto dto=new StudentDto();
		dto.setSid(s.getSid());
		dto.setName(s.getName());
		dto.setEmail(s.getEmail());
		dto.setCourse(s.getCourse());
		dto.setUniversity(s.getUniversity());
		dto.setResumeUrl(s.getResumeUrl());
		return dto;
	}

}
