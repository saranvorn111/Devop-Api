package com.example.devopapi.api.notification;

import com.example.devopapi.api.dto.CreateNotificationDto;
import com.example.devopapi.api.dto.NotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
