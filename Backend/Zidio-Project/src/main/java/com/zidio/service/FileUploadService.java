package com.zidio.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	public String upload(MultipartFile file)throws IOException;
}
