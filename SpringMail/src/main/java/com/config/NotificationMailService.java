package com.config;

import com.domain.Notification;

public interface NotificationMailService {
	
	void sendSimpleNotification(String to, Notification notification);
	void sendNotificationWithAttachment(String to, Notification notification);
}

/*
public interface SpitterMailService {

  public abstract void sendSimpleSpittleEmail(String to, Spittle spittle);

  public abstract void sendSpittleEmailWithAttachment(String to, Spittle spittle)
      throws MessagingException;

}
*/