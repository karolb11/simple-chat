package com.example.websockets;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import javax.websocket.OnOpen;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/message")
    public String processMessageFromClient(@Payload String messagePayload, Principal principal) throws Exception {
        String messageContent = new Gson().fromJson(messagePayload, Map.class).get("message").toString();
        String messageDestination = new Gson().fromJson(messagePayload, Map.class).get("destination").toString();
        Message message = Message.builder()
                .from(principal.getName())
                .content(messageContent)
                .to(messageDestination)
                .build();
        messagingTemplate.convertAndSendToUser(messageDestination, "/queue/reply", message);
        return message.content;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
