package com.nt.client;

import java.io.IOException;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="FILE-SERVICE",configuration=FeignConfig.class)
//@Component
public interface FileFindClient {
	@PostMapping(value="/api/file/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public Map<String,String> ResumeUrl(@RequestPart("file")MultipartFile file)throws IOException;
	
}
