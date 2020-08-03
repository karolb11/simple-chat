package com.example.websockets.controller;

import java.security.Principal;
import java.util.Map;

import com.example.websockets.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

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
        return message.getContent();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
