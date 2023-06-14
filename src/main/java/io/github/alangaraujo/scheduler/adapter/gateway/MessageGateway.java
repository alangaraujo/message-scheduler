package io.github.alangaraujo.scheduler.adapter.gateway;

import io.github.alangaraujo.scheduler.adapter.jpa.mapper.MessageEntityMapper;
import io.github.alangaraujo.scheduler.adapter.jpa.repository.MessageRepository;
import io.github.alangaraujo.scheduler.domain.model.Message;
import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotFoundException;
import io.github.alangaraujo.scheduler.domain.usecase.port.MessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageGateway implements MessagePort {

    private final MessageRepository messageRepository;
    private final MessageEntityMapper messageEntityMapper;

    @Override
    public Message create(Message message) {
        var entity = messageEntityMapper.toModel(message);
        entity = messageRepository.save(entity);
        return messageEntityMapper.toEntity(entity);
    }

    @Override
    public Message read(Long id) {
        return messageRepository.findById(id)
                .map(value -> messageEntityMapper.toEntity(value))
                .orElseThrow(() -> new MessageNotFoundException("Message not found: " + id));
    }

    @Override
    public void delete(Long id) {
        try {
            messageRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new MessageNotFoundException("Message not found: " + id);
        }
    }

    @Override
    public Boolean isSent(Long id) {
        return messageRepository.isSent(id);
    }

}
