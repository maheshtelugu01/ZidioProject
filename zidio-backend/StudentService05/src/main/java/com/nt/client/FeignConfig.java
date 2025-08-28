package com.nt.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@Configuration
public class FeignConfig {

	@Bean
	public Encoder fiegnFormEncoder() {
		return new SpringFormEncoder();
	}
}
