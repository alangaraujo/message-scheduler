package io.github.alangaraujo.scheduler.domain.usecase.port;

import io.github.alangaraujo.scheduler.domain.model.Message;

public interface MessagePort {

    Message create(Message message);
    Message read(Long id);
    void delete(Long id);
    Boolean isSent(Long id);

}
