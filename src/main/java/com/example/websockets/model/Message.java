package com.example.websockets.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String from;
    private String to;
    private String content;
}
