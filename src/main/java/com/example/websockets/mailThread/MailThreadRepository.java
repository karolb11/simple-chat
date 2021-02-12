package com.example.websockets.mailThread;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websockets.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailThreadRepository extends JpaRepository<MailThread, Long> {
    List<MailThreadDTO> findMailThreadDTOsByMembersContains(User user);

    @Query(value = "SELECT mail_message.thread_id as id, mail_thread.subject FROM mailthread_member INNER JOIN mail_thread ON mailthread_member.mail_threads_mail_thread_id = mail_thread.mail_thread_id INNER JOIN mail_message on mail_message.thread_id = mail_thread.mail_thread_id WHERE mailthread_member.members_user_id = :userId GROUP BY mail_message.thread_id, mail_thread.subject ORDER BY MAX(mail_message.created_at) DESC",
            nativeQuery = true)
    List<IMailThreadDTO> findMailThreadDTOByMemberIdOrderByLatestMsgDate(@Param("userId") Long userId);

    Optional<MailThreadDTO> findMailThreadDTOById(Long id);
}
