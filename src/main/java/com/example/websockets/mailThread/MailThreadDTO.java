package com.example.websockets.mailThread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailThreadDTO {
    private long id;
    private String subject;
}
