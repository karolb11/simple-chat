package com.example.websockets.mailMessage;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailMessageMapper {
    MailMessageDTO mapToDTO(MailMessage mailMessage);
}
