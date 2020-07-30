package com.example.websockets.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
    public static String CONNECTED = "connected";
    public static String DISCONNECTED = "disconnected";

    NotificationType type;
    String username;
}
