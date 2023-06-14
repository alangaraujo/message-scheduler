package io.github.alangaraujo.scheduler.adapter.api.exception;

import io.github.alangaraujo.scheduler.adapter.api.dto.ErrorDto;
import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotFoundException;
import io.github.alangaraujo.scheduler.domain.usecase.exception.MessageNotRemovableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final String INVALID_PAYLOAD = "Bad request: invalid payload or missing parameter";
    private final String INVALID_ID = "Invalid id in this URL path. Only integer values are allowed";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(INVALID_PAYLOAD, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(INVALID_PAYLOAD));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(INVALID_ID, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(INVALID_ID));
    }

    @ExceptionHandler(MessageNotRemovableException.class)
    public ResponseEntity handleMessageNotRemovableException(MessageNotRemovableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity handleMessageNotFound(MessageNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(ex.getMessage()));
    }

}
