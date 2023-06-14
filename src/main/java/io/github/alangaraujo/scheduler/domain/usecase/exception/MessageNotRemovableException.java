package io.github.alangaraujo.scheduler.domain.usecase.exception;

public class MessageNotRemovableException extends RuntimeException {

    public MessageNotRemovableException() {
        super("Message not removable, already sent");
    }

}
