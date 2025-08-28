package com.nt.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.service.IFileUploadService;

import jakarta.ws.rs.core.HttpHeaders;


@RestController
@RequestMapping("/api/file")
public class FileUploadController {
	@Autowired
	private IFileUploadService fileUploadService;
	@PostMapping("/upload")
	public ResponseEntity<Map<String,String>>upload(@RequestParam("file") MultipartFile file)throws IOException{
		String path=fileUploadService.upload(file);
		return ResponseEntity.ok(Map.of("resumeUrl",path));
	}
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadFromCloud(@RequestParam String url) {
	    try {
	        URL cloudUrl = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection) cloudUrl.openConnection();
	        connection.setRequestMethod("GET");

	        String fileName = "resume.pdf"; // default
	        String disposition = connection.getHeaderField("Content-Disposition");
	        if (disposition != null && disposition.contains("filename=")) {
	            fileName = disposition.split("filename=")[1].replace("\"", "");
	        }

	        try (InputStream in = connection.getInputStream()) {
	            byte[] fileBytes = in.readAllBytes();

	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                    .body(fileBytes);
	        }
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
}
