package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.model.MessageType;
import io.github.alangaraujo.scheduler.domain.usecase.port.SenderPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendMessageUseCase {

    private final SenderPort senderPort;

    public void send(Message message, MessageType messageType) {
        senderPort.send(message, messageType);
    }

}
