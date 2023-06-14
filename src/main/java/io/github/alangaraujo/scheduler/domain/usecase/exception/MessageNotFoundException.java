package io.github.alangaraujo.scheduler.domain.usecase.exception;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String message) {
        super(message);
    }
}
