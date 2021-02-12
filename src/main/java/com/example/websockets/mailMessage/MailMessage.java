package com.example.websockets.mailMessage;

import com.example.websockets.audit.Audit;
import com.example.websockets.mailThread.MailThread;
import com.example.websockets.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailMessage extends Audit {
    @Id
    @Column(name = "mail_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private MailThread thread;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
}
