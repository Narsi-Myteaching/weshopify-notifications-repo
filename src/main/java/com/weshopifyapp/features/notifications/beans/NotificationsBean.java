package com.weshopifyapp.features.notifications.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NotificationsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8260900101027085483L;
	private List<EmailRequest> to;
	private String subject;
	private String contentType;
	private int statusCode;
	

}
