package com;

import java.time.ZonedDateTime;
import java.util.Arrays;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MailTestConfig {
	
	@Bean
	public GreenMailBean greenMail() {
		GreenMailBean greenMail = new GreenMailBean();
		greenMail.setUsers(Arrays.asList("user123:abcpass@localserver.com")); 
		return greenMail;
	}	
}

