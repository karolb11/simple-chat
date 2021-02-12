package com.example.websockets.auth;

import lombok.Data;

@Data
public class SignInRequest {
    String username;
    String password;
}
