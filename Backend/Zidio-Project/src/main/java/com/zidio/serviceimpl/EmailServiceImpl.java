package com.zidio.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio.dto.EmailRequest;
import com.zidio.service.EmailService;
//@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	//private JavaMailSender mailSender;
	
	@Override
	public String sendEmail(EmailRequest req) {
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setTo(req.getTo());
//		message.setSubject(req.getSubject());
//		message.setText(req.getMessage());
//		message.setFrom("maheshtelugu@gmail.com");
//		message.send(message);
	return "Email sent Successfully";
	}

}
