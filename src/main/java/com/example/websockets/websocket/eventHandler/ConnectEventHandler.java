package com.example.websockets.websocket.eventHandler;

import com.example.websockets.notification.Notification;
import com.example.websockets.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ConnectEventHandler {
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void onSessionConnect(SessionConnectEvent event) {
        String username = Objects.requireNonNull(event.getUser()).getName();
        Notification notification = Notification.builder()
                .type(NotificationType.CONNECT)
                .username(username)
                .build();
        messagingTemplate.convertAndSend("/topic/public", notification);
    }
}
