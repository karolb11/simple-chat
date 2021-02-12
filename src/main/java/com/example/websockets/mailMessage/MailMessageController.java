package com.example.websockets.mailMessage;

import com.example.websockets.user.User;
import com.example.websockets.user.UserDTO;
import com.example.websockets.user.UserMapper;
import com.example.websockets.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/mail-message")
public class MailMessageController {
    private final MailMessageRepository mailMessageRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/{id}/author")
    @ResponseBody
    public UserDTO findAuthorByMailMessageId(@PathVariable Long id) {
        MailMessage message = mailMessageRepository.findById(id).get();
        User author = message.getAuthor();
        return userMapper.mapToDTO(author);
    }
}
