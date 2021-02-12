package com.example.websockets.mailMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MailMessageDTO {
    private Long id;
    private String content;
    private Date createdAt;
}
