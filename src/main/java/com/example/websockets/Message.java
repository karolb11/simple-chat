package com.example.websockets;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    String from;
    String to;
    String content;
}
