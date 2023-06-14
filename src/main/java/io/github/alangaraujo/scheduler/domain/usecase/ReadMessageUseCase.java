package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadMessageUseCase {

    private final MessagePort messagePort;

    public Message read(Long id) {
        return messagePort.read(id);
    }

}
