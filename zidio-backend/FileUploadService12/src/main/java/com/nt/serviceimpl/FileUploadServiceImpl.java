package com.nt.serviceimpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nt.service.IFileUploadService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FileUploadServiceImpl implements IFileUploadService {
	@Autowired
	private Cloudinary cloudinary;
	@Override
	public String upload(MultipartFile file) throws IOException {
		 try {
			 log.info("upload(-)-->FileService");
			 Map<String,Object>uploadResult=cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap("folder","resumes",
					 "resource_type","raw","access_mode","public"));
			 return uploadResult.get("secure_url").toString();
		 }catch(IOException e) {
			 e.printStackTrace();
			 throw new RuntimeException("Failed to upload files",e);
		 }
	}

}
