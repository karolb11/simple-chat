package com.example.websockets.mailMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {
    List<MailMessageDTO> findMailMessageDTOsByThreadId(Long threadId);

    List<MailMessageDTO> findMailMessageDTOsByThreadIdOrderByCreatedAtDesc(Long threadId);
}
