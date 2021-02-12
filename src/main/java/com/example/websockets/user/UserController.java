package com.example.websockets.user;

import com.example.websockets.ApiResponse;
import com.example.websockets.mailThread.IMailThreadDTO;
import com.example.websockets.mailThread.MailThread;
import com.example.websockets.mailThread.MailThreadDTO;
import com.example.websockets.mailThread.MailThreadRepository;
import com.example.websockets.security.CurrentUser;
import com.example.websockets.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final MailThreadRepository mailThreadRepository;

    @GetMapping("/me")
    @ResponseBody
    public UserDTO findMe(@CurrentUser UserPrincipal currentUser) {
        return userRepository.findUserDTOById(currentUser.getId()).get();
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public UserDTO findUserById(@PathVariable Long userId) {
        return userRepository.findUserDTOById(userId).get();
    }

    @GetMapping("/{userId}/mail-thread")
    @ResponseBody
    public List<IMailThreadDTO> findMailThreadByUserId(@PathVariable Long userId) {
        return mailThreadRepository.findMailThreadDTOByMemberIdOrderByLatestMsgDate(userId);
    }
}
