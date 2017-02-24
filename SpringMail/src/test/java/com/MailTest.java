package com;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.config.MailConfig;
import com.config.NotificationMailService;
import com.domain.Notification;
import com.icegreen.greenmail.spring.GreenMailBean;
import org.junit.Assert;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:mail.properties")
@ContextConfiguration(classes={MailConfig.class, MailTestConfig.class})
public class MailTest {
	
	@Autowired
	Environment env;
	
	@Autowired
	NotificationMailService notificationMailService;
	
	@Autowired
	GreenMailBean greenMail;
	
	// Server:
	@Bean
	public GreenMailBean greenMail(Environment env) {
		GreenMailBean greenMail = new GreenMailBean();
		// same as in mail.properties.
		greenMail.setUsers(Arrays.asList("user123:abcpass@whatever.com"));  // @whatever.com
		return greenMail;
	}
	
	@Test
	public void test() throws IOException, MessagingException {
		Assert.assertEquals("localhost", env.getProperty("mailserver.host"));
		
		Notification notification = new Notification();
		notification.setLattitude(-1.0);
		notification.setLongitude(2.0);
		notification.setMessage("Today is a great day message kind of.");
		notification.setTimestamp(ZonedDateTime.now());
		
		notificationMailService.sendSimpleNotification("newuser@otherserver.com", notification);
		
		MimeMessage[] messages = greenMail.getReceivedMessages();
		Assert.assertEquals(1, messages.length);
		
		Assert.assertEquals(notification.toString(), messages[0].getContent().toString().trim());
		//messages[0].getEncoding()
		InternetAddress from = (InternetAddress) messages[0].getFrom()[0];
		Assert.assertEquals("noreply@whatever.com", from.getAddress());
		InternetAddress recipient = (InternetAddress) messages[0].getRecipients(RecipientType.TO)[0];
		Assert.assertEquals("newuser@otherserver.com", recipient.getAddress());
	}
}

