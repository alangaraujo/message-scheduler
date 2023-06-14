package io.github.alangaraujo.scheduler.domain.usecase;

import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotRemovableException;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteMessageUseCaseTest {

    @InjectMocks
    private DeleteMessageUseCase deleteMessageUseCase;

    @Mock
    private MessagePort messagePort;

    @Test
    public void givenAnIdWhenDeleteAMessageThenCallMessagePort() {

        var id = 1L;

        deleteMessageUseCase.delete(id);

        verify(messagePort).delete(id);

    }

    @Test()
    public void givenAnInvalidIdWhenDeleteAMessageThenThrowAnException() {

        var id = Long.MAX_VALUE;
        when(messagePort.isSent(id)).thenReturn(true);

        assertThatThrownBy(() -> deleteMessageUseCase.delete(id))
                .isExactlyInstanceOf(MessageNotRemovableException.class)
                .hasMessage("Message not removable, already sent");

        verify(messagePort).isSent(id);

    }

}
