package com.nt.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.dto.StudentDto;
import com.nt.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private IStudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody StudentDto dto) {
		return ResponseEntity.ok(studentService.saveStudent(dto));
	}

	@GetMapping("/find/{studentEmail}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable String studentEmail) {
		return ResponseEntity.ok(studentService.getStudent(studentEmail));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> StudentCount() {
		return ResponseEntity.ok(studentService.studentCount());
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<StudentDto>>findAll() {
		return ResponseEntity.ok(studentService.findAll());
	}
	@PostMapping(value="/uploadresume",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String>uploadResume(@RequestPart("file")MultipartFile file)throws IOException {
		return ResponseEntity.ok(studentService.updateStudentResume(file));
	}
	@GetMapping("/email/findAll")
	public ResponseEntity<List<String>>findAllEmails() {
		return ResponseEntity.ok(studentService.findAllEmails());
	}

}
