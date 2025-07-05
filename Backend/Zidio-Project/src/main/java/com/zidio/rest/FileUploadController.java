package com.zidio.rest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zidio.service.FileUploadService;


@RestController
@RequestMapping("/api/file")
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	@PostMapping("/upload")
	public ResponseEntity<Map<String,String>>upload(@RequestParam("file") MultipartFile file)throws IOException{
		String path=fileUploadService.upload(file);
		return ResponseEntity.ok(Map.of("ResumeUrl",path));
	}
	@GetMapping("/{fileName}")
	public ResponseEntity<Resource>download(@PathVariable String fileName)throws IOException{
		Path path=Paths.get("upload/resumes").resolve(fileName);
		Resource resource=new UrlResource(path.toUri());
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	
}
