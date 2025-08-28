package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.InternshipDto;
import com.nt.service.IInternshipService;

@RestController
@RequestMapping("/api/internship")
public class InternshipController {
	@Autowired
	private IInternshipService internService;

	@GetMapping("/findAll")
	public ResponseEntity<List<InternshipDto>> getAllInternships() {
		return ResponseEntity.ok(internService.getAllInternships());
	}

	@GetMapping("/find/title/{title}")
	public ResponseEntity<List<InternshipDto>> searchByTitle(@PathVariable String title) {
		return ResponseEntity.ok(internService.searchByTitle(title));

	}

	@GetMapping("/find/companyName/{companyName}")
	public ResponseEntity<List<InternshipDto>> searchByCompanyName(@PathVariable String companyName) {
		return ResponseEntity.ok(internService.searchByCompanyName(companyName));
	}
	@PostMapping("/post")
	public ResponseEntity<String> postInternship(@RequestBody InternshipDto dto){
		return ResponseEntity.ok(internService.postInternships(dto));
	}
	@GetMapping("/find/{recuiterEmail}")
	public ResponseEntity<List<InternshipDto>>getInternshipsByRecuiter(@PathVariable String recuiterEmail){
		return ResponseEntity.ok(internService.getPostedInternships(recuiterEmail));
	}
	@GetMapping("/yourInternships")
	public ResponseEntity<List<InternshipDto>>yourInternships(){
		return ResponseEntity.ok(internService.yourInternships());
	}
	@GetMapping("/yourInternshipIds")
	public ResponseEntity<List<Long>>yourInternshipIds(){
		return ResponseEntity.ok(internService.yourInternshipIds());
	}
	@GetMapping("/count")
	public ResponseEntity<Long> internshipCount(){
		return ResponseEntity.ok(internService.internshipCount());
	}
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<String> DeactivateInternship(@PathVariable Long id){
		return ResponseEntity.ok(internService.deactivateInternship(id));
	}
	
}
