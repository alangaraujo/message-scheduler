package io.github.alangaraujo.scheduler.adapter.api.dto;

import io.github.alangaraujo.scheduler.domain.model.Status;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageScheduleStatusDto {

    private Long id;
    private Status status;

}
