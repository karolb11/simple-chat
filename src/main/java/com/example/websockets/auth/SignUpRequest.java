package com.example.websockets.auth;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
