package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name","dj2qcvear",
				"api_key","911637373231461",
				"api_secret","2uYs2e0Hujs6xFBZKS_4Vz1oNPI",
				"secure",true));
	}
}
