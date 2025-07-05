package com.zidio.service;

import com.zidio.dto.EmailRequest;

public interface EmailService {
	public String sendEmail(EmailRequest req);
}
