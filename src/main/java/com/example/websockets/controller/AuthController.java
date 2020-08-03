package com.example.websockets.controller;

import com.example.websockets.model.RoleName;
import com.example.websockets.model.User;
import com.example.websockets.payload.SignUpRequest;
import com.example.websockets.repository.RoleRepository;
import com.example.websockets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER)))
                .build();
        userRepository.save(user);
        return "ok";
    }
}
