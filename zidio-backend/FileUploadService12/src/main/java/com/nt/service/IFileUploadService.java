package com.nt.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
	public String upload(MultipartFile file)throws IOException;
}
