package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.model.MessageType;
import io.github.alangaraujo.scheduler.domain.usecase.port.SenderPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SendMessageUseCaseTest {

    @InjectMocks
    private SendMessageUseCase sendMessageUseCase;

    @Mock
    private SenderPort senderPort;

    @Test
    public void givenAMessageWhenSendThenCallSenderPort() {

        var message = Message.builder().build();
        var messageTypeEntity = MessageType.EMAIL;

        sendMessageUseCase.send(message, messageTypeEntity);

        verify(senderPort).send(message, messageTypeEntity);

    }

}
