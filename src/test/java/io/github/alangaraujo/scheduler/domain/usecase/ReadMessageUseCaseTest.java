package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReadMessageUseCaseTest {

    @InjectMocks
    private ReadMessageUseCase readMessageUseCase;

    @Mock
    private MessagePort messagePort;

    @Test
    public void givenAnIdWhenReadAMessageThenReturnAMessage() {

        var id = 1L;
        var message = Message.builder().build();

        when(messagePort.read(id)).thenReturn(message);
        var read = readMessageUseCase.read(id);

        assertThat(read).isSameAs(message);

        verify(messagePort).read(id);

    }

}
