package com.example.websockets.controller;

import com.example.websockets.model.User;
import com.example.websockets.payload.ApiResponse;
import com.example.websockets.security.CurrentUser;
import com.example.websockets.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/me")
    public ApiResponse getUsername(@CurrentUser UserPrincipal currentUser) {
        return new ApiResponse(currentUser.getUsername());
    }
}
