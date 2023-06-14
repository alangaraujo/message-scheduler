package io.github.alangaraujo.scheduler.adapter.api.dto;

import io.github.alangaraujo.scheduler.domain.model.MessageType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageScheduleCreateDto {

    @NotNull
    private MessageType messageType;

    @NotNull
    private OffsetDateTime scheduleDateTime;

    @NotNull
    private String destination;

    @NotBlank
    private String message;

}
