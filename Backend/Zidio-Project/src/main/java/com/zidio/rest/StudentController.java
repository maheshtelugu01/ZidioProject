package com.zidio.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.dto.StudentDto;
import com.zidio.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/profile")
	public ResponseEntity<String>save(@RequestBody StudentDto dto){
		return  ResponseEntity.ok(studentService.createOrUpdate(dto));
	}
	
	@GetMapping("/profile/{email}")
	public ResponseEntity<StudentDto>get(@PathVariable String email){
		return ResponseEntity.ok(studentService.getProfile(email));
	}
}
