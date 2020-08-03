package com.example.websockets.payload;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
