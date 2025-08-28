package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.RecuiterDto;
import com.nt.service.IRecuiterService;

@RestController
@RequestMapping("/api/recuiter")
public class RecuiterController {
	@Autowired
	private IRecuiterService recuiterService;

	@PostMapping(value="/save")
	public ResponseEntity<String> saveProfile(@RequestBody RecuiterDto dto) {
		return ResponseEntity.ok(recuiterService.saveProfile(dto));
	}

	@GetMapping("find/{recuiterEmail}")
	public ResponseEntity<RecuiterDto> getByRecuiterEmail(@PathVariable String recuiterEmail) {
		return ResponseEntity.ok(recuiterService.getByRecuiterEmail(recuiterEmail));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<RecuiterDto>> getAllRecuiters() {
		return ResponseEntity.ok(recuiterService.getAllRecuiters());
	}

	@GetMapping("/count")
	public ResponseEntity<Long> RecuiterCount() {
		return ResponseEntity.ok(recuiterService.recuiterCount());
	}

}
