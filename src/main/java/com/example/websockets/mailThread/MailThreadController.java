package com.example.websockets.mailThread;

import com.example.websockets.mailMessage.*;
import com.example.websockets.security.CurrentUser;
import com.example.websockets.security.UserPrincipal;
import com.example.websockets.user.User;
import com.example.websockets.user.UserDTO;
import com.example.websockets.user.UserMapper;
import com.example.websockets.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/mail-thread")
public class MailThreadController {
    private final MailThreadRepository mailThreadRepository;
    private final MailMessageRepository mailMessageRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailMessageMapper mailMessageMapper;
    private final ReplyNotifier replyNotifier;

    @GetMapping("/{mailThreadId}")
    @ResponseBody
    public MailThreadDTO findMailThreadById(@PathVariable Long mailThreadId) {
        return mailThreadRepository.findMailThreadDTOById(mailThreadId).get();
    }

    @GetMapping("/{mailThreadId}/members")
    @ResponseBody
    public List<UserDTO> findMembersMyThreadId(@PathVariable Long mailThreadId) {
        MailThread thread = mailThreadRepository.findById(mailThreadId).get();
        List<User> members = thread.getMembers();
        return members.stream().map(userMapper::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{mailThreadId}/message")
    @ResponseBody
    public List<MailMessageDTO> findMailMessagesByThreadId(@PathVariable Long mailThreadId) {
        return mailMessageRepository.findMailMessageDTOsByThreadId(mailThreadId);
    }

    @GetMapping("/{mailThreadId}/message/newest")
    @ResponseBody
    public MailMessageDTO findNewestMailMessagesByThreadId(@PathVariable Long mailThreadId) {
        return mailMessageRepository.findMailMessageDTOsByThreadIdOrderByCreatedAtDesc(mailThreadId).get(0);
    }

    @PostMapping("/{mailThreadId}/message")
    @ResponseBody
    public MailMessageDTO createMessage(@PathVariable("mailThreadId") Long threadId,
                                        @RequestBody MailMessageDTO messageDTO,
                                        @CurrentUser UserPrincipal currentUser) {
        MailMessage message = MailMessage.builder()
                .author(userRepository.findById(currentUser.getId()).get())
                .content(messageDTO.getContent())
                .thread(mailThreadRepository.findById(threadId).get())
                .build();
        MailMessage savedMessage = mailMessageRepository.save(message);
        replyNotifier.notify(savedMessage);
        return mailMessageMapper.mapToDTO(savedMessage);
    }
}
