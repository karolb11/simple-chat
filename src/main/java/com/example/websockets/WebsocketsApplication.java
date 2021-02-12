package com.example.websockets;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.websockets.mailThread.MailThreadRepository;
import com.example.websockets.user.UserRepository;
import com.example.websockets.websocket.HttpHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class WebsocketsApplication {

    @Bean(name = "httpHandshakeInterceptor")
    public HttpHandshakeInterceptor httpHandshakeInterceptor() {
        return new HttpHandshakeInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsocketsApplication.class, args);

    }

}
