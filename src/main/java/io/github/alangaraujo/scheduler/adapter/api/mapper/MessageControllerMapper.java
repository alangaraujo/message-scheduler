package io.github.alangaraujo.scheduler.adapter.api.mapper;

import io.github.alangaraujo.scheduler.adapter.api.dto.MessageScheduleCreateDto;
import io.github.alangaraujo.scheduler.adapter.api.dto.MessageScheduleStatusDto;
import io.github.alangaraujo.scheduler.domain.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageControllerMapper {

    public MessageScheduleStatusDto toDto(Message message) {
        return MessageScheduleStatusDto.builder()
                .id(message.getId())
                .status(message.getStatus())
                .build();
    }

    public Message toEntity(MessageScheduleCreateDto messageScheduleCreateDto) {
        return Message.builder()
                .messageType(messageScheduleCreateDto.getMessageType())
                .scheduledDateTime(messageScheduleCreateDto.getScheduleDateTime())
                .destination(messageScheduleCreateDto.getDestination())
                .message(messageScheduleCreateDto.getMessage())
                .build();
    }

}
