package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotRemovableException;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMessageUseCase {

    private final MessagePort messagePort;

    public void delete(Long id) {
        if (messagePort.isSent(id)) {
            throw new MessageNotRemovableException();
        }

        messagePort.delete(id);
    }

}
