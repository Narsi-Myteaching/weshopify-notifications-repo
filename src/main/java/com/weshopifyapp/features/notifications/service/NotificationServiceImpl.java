package com.weshopifyapp.features.notifications.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.weshopifyapp.features.notifications.beans.NotificationsBean;
import com.weshopifyapp.features.notifications.outbound.MailSender;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	private MailSender mailSender;

	NotificationServiceImpl(MailSender mailSender){
		this.mailSender = mailSender;
	}
	
	@Override
	public NotificationsBean sendVerificationEmail(NotificationsBean notificationBean) {
		Map<String, Object> model = new HashMap<>();
	
		return mailSender.sendVerificationEmail(notificationBean,model);
	}

}
