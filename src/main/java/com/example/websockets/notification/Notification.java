package com.example.websockets.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
    NotificationType type;
    String username;
}
