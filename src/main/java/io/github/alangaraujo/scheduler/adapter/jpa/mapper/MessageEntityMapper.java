package io.github.alangaraujo.scheduler.adapter.jpa.mapper;

import io.github.alangaraujo.scheduler.adapter.jpa.entity.MessageEntity;
import io.github.alangaraujo.scheduler.domain.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageEntityMapper {

    public MessageEntity toModel(Message message) {
        return MessageEntity.builder()
                .id(message.getId())
                .status(message.getStatus())
                .messageType(message.getMessageType())
                .scheduledDateTime(message.getScheduledDateTime())
                .destination(message.getDestination())
                .message(message.getMessage())
                .build();
    }

    public Message toEntity(MessageEntity messageEntity) {
        return Message.builder()
                .id(messageEntity.getId())
                .status(messageEntity.getStatus())
                .messageType(messageEntity.getMessageType())
                .scheduledDateTime(messageEntity.getScheduledDateTime())
                .destination(messageEntity.getDestination())
                .message(messageEntity.getMessage())
                .build();
    }

}
