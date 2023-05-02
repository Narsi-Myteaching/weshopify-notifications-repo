package com.weshopifyapp.features.notifications.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyapp.features.notifications.beans.NotificationsBean;
import com.weshopifyapp.features.notifications.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NotificationsResource {

	@Autowired
	private NotificationService service;
	
	@PostMapping(value = "/notifications")
	public ResponseEntity<Object> postNotification(@RequestBody NotificationsBean notificationsBean){
		log.info("The Notifications Data is:\t"+notificationsBean.toString());
		notificationsBean = service.sendVerificationEmail(notificationsBean);
		if(HttpStatus.ACCEPTED.value() == notificationsBean.getStatusCode()) {
			return ResponseEntity.ok(notificationsBean);
		}else {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("message", "Email Notification has not been delivered");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
		}
	}
	
}
