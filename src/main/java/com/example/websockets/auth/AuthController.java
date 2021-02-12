package com.example.websockets.auth;

import com.example.websockets.role.RoleName;
import com.example.websockets.user.User;
import com.example.websockets.ApiResponse;
import com.example.websockets.repository.RoleRepository;
import com.example.websockets.user.UserDTO;
import com.example.websockets.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserDTO signIn(@RequestBody SignInRequest signInRequest) {
        authService.signIn(signInRequest);
        return userRepository.findUserDTOByUsername(signInRequest.username).get();
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
