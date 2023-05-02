package com.weshopifyapp.features.notifications.service;

import com.weshopifyapp.features.notifications.beans.NotificationsBean;

public interface NotificationService {

	NotificationsBean sendVerificationEmail(NotificationsBean notificationBean);
}
