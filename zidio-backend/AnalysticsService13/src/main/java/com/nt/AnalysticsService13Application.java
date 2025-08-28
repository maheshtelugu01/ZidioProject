package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableDiscoveryClient
public class AnalysticsService13Application {
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
	@Bean
	public RestTemplate restTemplate(HttpServletRequest request) {
		RestTemplate template=new RestTemplate();
		template.getInterceptors().add((req,body,exec)->{
			String token=request.getHeader("Authorization");
			if(token!=null) {
				req.getHeaders().add("Authorization", token);
			}
			return exec.execute(req, body);
		});
		return template;
	}
	public static void main(String[] args) {
		SpringApplication.run(AnalysticsService13Application.class, args);
	}

}
