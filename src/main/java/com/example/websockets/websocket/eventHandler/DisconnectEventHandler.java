package com.example.websockets.websocket.eventHandler;

import com.example.websockets.notification.Notification;
import com.example.websockets.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DisconnectEventHandler {
    private final SimpMessageSendingOperations messagingTemplate;

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
