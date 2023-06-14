package io.github.alangaraujo.scheduler.adapter.jpa.entity;

import io.github.alangaraujo.scheduler.domain.model.MessageType;
import io.github.alangaraujo.scheduler.domain.model.Status;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "Message")
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private OffsetDateTime scheduledDateTime;
    private String destination;
    private String message;

}
