package com.weshopifyapp.features.notifications;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.weshopifyapp.features.notifications.beans.EmailRequest;
import com.weshopifyapp.features.notifications.beans.NotificationsBean;
import com.weshopifyapp.features.notifications.service.NotificationService;

@SpringBootTest
class WeshopifyNotificationsServiceApplicationTests {

	@Autowired
	private NotificationService notificationService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void sendVerificationEmail() {
		NotificationsBean notification = new NotificationsBean();
		//notification.setFrom("weshopifyapp@yopmail.com");
		
		List<EmailRequest> toList = new ArrayList<>();
		
		EmailRequest toEmail = new EmailRequest();
		toEmail.setEmail("narsi.myteaching@gmail.com");
		
		EmailRequest toEmail2 = new EmailRequest();
		toEmail2.setEmail("narsi.myBooks@gmail.com");
		
		toList.add(toEmail2);
		toList.add(toEmail);
		
		notification.setTo(toList);
		notification.setContentType(MediaType.TEXT_HTML_VALUE);
		notification.setSubject("Welcome To WeshopifyApp Platorm");
		
		notificationService.sendVerificationEmail(notification);
	}
}
