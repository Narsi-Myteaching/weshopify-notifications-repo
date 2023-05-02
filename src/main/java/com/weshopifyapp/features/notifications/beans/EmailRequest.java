package com.weshopifyapp.features.notifications.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7907205068999285706L;

	private String email;
	private String userId;
	private int id;
}
