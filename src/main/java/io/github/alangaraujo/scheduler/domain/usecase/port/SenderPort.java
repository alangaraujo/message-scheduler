package io.github.alangaraujo.scheduler.domain.usecase.port;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.model.MessageType;

public interface SenderPort {

    void send(Message message, MessageType messageType);

}
