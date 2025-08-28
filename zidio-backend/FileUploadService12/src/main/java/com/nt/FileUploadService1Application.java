package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileUploadService1Application {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadService1Application.class, args);
	}

}
