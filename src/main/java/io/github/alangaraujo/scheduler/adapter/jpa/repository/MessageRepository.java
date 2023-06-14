package io.github.alangaraujo.scheduler.adapter.jpa.repository;

import io.github.alangaraujo.scheduler.adapter.jpa.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT COUNT(message.status) > 0 AS sent FROM Message message WHERE message.id = :id AND message.status LIKE 'SENT'")
    Boolean isSent(Long id);

}
