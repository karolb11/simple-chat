package com.example.websockets.eventHandler;

import com.example.websockets.notification.Notification;
import com.example.websockets.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class DisconnectEventHandler {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void onSessionDisconnect(SessionDisconnectEvent event) {
        String username = Objects.requireNonNull(event.getUser()).getName();
        Notification notification = Notification.builder()
                .type(NotificationType.DISCONNECT)
                .username(username)
                .build();
        messagingTemplate.convertAndSend("/topic/public", notification);
    }
}
