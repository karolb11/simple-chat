package com.example.websockets.mailMessage;

import com.example.websockets.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyNotifier {
    private final SimpMessageSendingOperations messagingTemplate;

    public void notify(MailMessage mailMessage) {
        List<User> members = mailMessage.getThread().getMembers();
        NewMessageNotificationDTO notification = NewMessageNotificationDTO.builder()
                .id(mailMessage.getId())
                .content(mailMessage.getContent())
                .createdAt(mailMessage.getCreatedAt())
                .author(mailMessage.getAuthor().getUsername())
                .threadId(mailMessage.getThread().getId())
                .build();
        for (User member : members) {
            messagingTemplate.convertAndSendToUser(member.getUsername(), "/queue/reply", notification);
        }
    }
}
