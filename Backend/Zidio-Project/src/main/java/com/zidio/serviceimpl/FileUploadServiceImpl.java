package com.zidio.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zidio.service.FileUploadService;
@Service
public class FileUploadServiceImpl implements FileUploadService {
	//@Value("${file.upload-dir}")
	private String UploadDir;
	@Override
	public String upload(MultipartFile file) throws IOException {
		Path path=Paths.get(UploadDir);
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		String fileName=UUID.randomUUID()+"_"+file.getOriginalFilename();
		Path targetPath=path.resolve(fileName);
		Files.copy(file.getInputStream(),targetPath,StandardCopyOption.REPLACE_EXISTING);
		return "/files"+fileName;
	}

}
