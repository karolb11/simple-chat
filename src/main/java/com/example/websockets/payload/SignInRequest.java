package com.example.websockets.payload;

import lombok.Data;

@Data
public class SignInRequest {
    String username;
    String password;
}
