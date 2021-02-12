package com.example.websockets.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRestController {

    private final SimpUserRegistry simpUserRegistry;


    @GetMapping("/connectedUsers")
    public List<String> getConnectedUsers() {
        return simpUserRegistry
                .getUsers()
                .stream()
                .map(SimpUser::getName)
                .collect(Collectors.toList());
    }

}
