package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateMessageUseCaseTest {

    @InjectMocks
    private CreateMessageUseCase createMessageUseCase;

    @Mock
    private MessagePort messagePort;

    @Test
    public void givenAMessageWhenCreateThenCallPort() {
        var messageToCreate = Message.builder().build();
        var messageCreated = Message.builder().build();

        when(messagePort.create(messageToCreate)).thenReturn(messageCreated);

        assertThat(createMessageUseCase.create(messageToCreate))
                .isSameAs(messageCreated);

        verify(messagePort).create(messageToCreate);
    }

}
