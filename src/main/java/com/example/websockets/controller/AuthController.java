package com.example.websockets.controller;

import com.example.websockets.model.RoleName;
import com.example.websockets.model.User;
import com.example.websockets.payload.ApiResponse;
import com.example.websockets.payload.SignInRequest;
import com.example.websockets.payload.SignUpRequest;
import com.example.websockets.repository.RoleRepository;
import com.example.websockets.repository.UserRepository;
import com.example.websockets.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody SignInRequest signInRequest) {
        authService.signIn(signInRequest);
        return new ApiResponse("ok");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER)))
                .build();
        userRepository.save(user);
        ApiResponse res = new ApiResponse("ok");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
