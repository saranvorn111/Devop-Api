package com.example.devopapi.api.notification;

import com.example.devopapi.api.base.BestRest;
import com.example.devopapi.api.dto.CreateNotificationDto;
import com.example.devopapi.api.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public BestRest<?> pushNotification(@RequestBody CreateNotificationDto notificationDto){
        notificationService.pushNotification(notificationDto);
        return null;
    }
}
