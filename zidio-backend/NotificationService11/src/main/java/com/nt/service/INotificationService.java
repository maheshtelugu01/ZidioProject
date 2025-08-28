package com.nt.service;

import java.util.List;

import com.nt.dto.EmailRequest;
import com.nt.dto.NotificationDto;
import com.nt.entity.Notification;

public interface INotificationService {
	public String sendEmail(EmailRequest req);	 
	public void saveNotification(NotificationDto dto);
	public List<NotificationDto> getAllNotifications();
}
