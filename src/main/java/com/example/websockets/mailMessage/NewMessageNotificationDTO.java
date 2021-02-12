package com.example.websockets.mailMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageNotificationDTO {
    private Long id;
    private String content;
    private Date createdAt;
    private String author;
    private Long threadId;
}
