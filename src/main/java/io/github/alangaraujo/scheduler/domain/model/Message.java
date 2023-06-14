package io.github.alangaraujo.scheduler.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Message {

    private final Long id;

    @Builder.Default
    private final Status status = Status.SCHEDULED;

    private final MessageType messageType;
    private final OffsetDateTime scheduledDateTime;
    private final String destination;
    private final String message;

}
