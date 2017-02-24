package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.domain.Notification;

@Service
public class NotificationMailServiceImpl implements NotificationMailService {
	
	@Autowired
	public MailSender mailSender;

	@Override
	public void sendSimpleNotification(String to, Notification notification) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("noreply@whatever.com");
		mailMessage.setTo(to);
		mailMessage.setSubject("New message from whatever.com");
		mailMessage.setText(notification.toString());
		
		mailSender.send(mailMessage);
	}

	@Override
	public void sendNotificationWithAttachment(String to, Notification notification) {
		// TODO Auto-generated method stub
		
	}
	

}

