package io.github.alangaraujo.scheduler.adapter.gateway;

import io.github.alangaraujo.scheduler.adapter.jpa.mapper.MessageEntityMapper;
import io.github.alangaraujo.scheduler.adapter.jpa.entity.MessageEntity;
import io.github.alangaraujo.scheduler.adapter.jpa.repository.MessageRepository;
import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.model.Status;
import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageGatewayTest {

    @InjectMocks
    private MessageGateway messageGateway;

    @Mock
    private MessageRepository messageRepository;

    @Spy
    private MessageEntityMapper messageEntityMapper;

    @Test
    public void givenANewMessageWhenCreateThenUseRepository() {

        var message = Message.builder().build();
        var messageModel = MessageEntity.builder().status(Status.SCHEDULED).build();

        when(messageRepository.save(any(MessageEntity.class))).thenReturn(messageModel);

        var messageScheduled = messageGateway.create(message);

        assertThat(messageScheduled.getStatus()).isEqualTo(message.getStatus());

        verify(messageRepository).save(any(MessageEntity.class));
        verify(messageEntityMapper).toEntity(messageModel);
        verify(messageEntityMapper).toModel(message);

    }

    @Test
    public void givenAnMessageIdThenReturnAMessage() {

        var id = 1L;
        var messageModel = MessageEntity.builder().id(id).status(Status.SCHEDULED).build();

        when(messageRepository.findById(any())).thenReturn(Optional.of(messageModel));

        var message = messageGateway.read(id);

        assertThat(message.getStatus()).isEqualTo(messageModel.getStatus());
        assertThat(message.getId()).isEqualTo(messageModel.getId());

        verify(messageRepository).findById(id);
        verify(messageEntityMapper).toEntity(messageModel);

    }

    @Test
    public void givenAnValidIdWhenCallDeleteThenDeleteAMessage() {

        var id = 1L;

        messageGateway.delete(id);

        verify(messageRepository).deleteById(id);

    }

    @Test
    public void givenAnInvalidIdThenThrowAnException() {

        var id = 1L;

        when(messageRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> messageGateway.read(id))
                .isExactlyInstanceOf(MessageNotFoundException.class)
                .hasMessage("Message not found: " + id);

    }

    @Test
    public void givenAnInvalidIdWhenDeleteThenThrowAnException() {

        var id = 1L;

        doThrow(EmptyResultDataAccessException.class).when(messageRepository).deleteById(id);

        assertThatThrownBy(() -> messageGateway.delete(id))
                .isExactlyInstanceOf(MessageNotFoundException.class)
                .hasMessage("Message not found: " + id);

    }

    @Test
    public void givenASentMessageWhenDeletingThenThrowException() {

        var id = 1L;

        when(messageRepository.isSent(id)).thenReturn(true);

        assertThat(messageGateway.isSent(id)).isTrue();

        verify(messageRepository).isSent(id);

    }

}
