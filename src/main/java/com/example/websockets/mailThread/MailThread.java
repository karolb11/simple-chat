package com.example.websockets.mailThread;

import com.example.websockets.user.User;
import com.example.websockets.mailMessage.MailMessage;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "mail_thread")
public class MailThread {
    @Id
    @Column(name = "mail_thread_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "mailthread_member")
    private List<User> members;

    @OneToMany(mappedBy = "thread")
    private List<MailMessage> messages;
}
