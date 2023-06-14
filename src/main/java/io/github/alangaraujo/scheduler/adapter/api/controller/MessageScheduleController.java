package io.github.alangaraujo.scheduler.adapter.api.controller;

import io.github.alangaraujo.scheduler.adapter.api.dto.ErrorDto;
import io.github.alangaraujo.scheduler.adapter.api.dto.MessageScheduleCreateDto;
import io.github.alangaraujo.scheduler.adapter.api.mapper.MessageControllerMapper;
import io.github.alangaraujo.scheduler.domain.usecase.CreateMessageUseCase;
import io.github.alangaraujo.scheduler.domain.usecase.DeleteMessageUseCase;
import io.github.alangaraujo.scheduler.domain.usecase.ReadMessageUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageScheduleController {

    private final CreateMessageUseCase createMessageUseCase;
    private final ReadMessageUseCase readMessageUseCase;
    private final DeleteMessageUseCase deleteMessageUseCase;
    private final MessageControllerMapper mapper;

    @ApiOperation(value = "Set a message notification", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Scheduled message notification."),
            @ApiResponse(code = 400, response = ErrorDto.class, message = "Invalid payload or missing parameter")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody @Valid @ApiParam(name = "Message Create", value = "Message Body Details") MessageScheduleCreateDto messageScheduleCreateDto) {
        var message = mapper.toEntity(messageScheduleCreateDto);
        message = createMessageUseCase.create(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(message));
    }

    @ApiOperation(value = "Get message notification status", produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, response = ErrorDto.class, message = "Invalid ID in URL path"),
            @ApiResponse(code = 404, response = ErrorDto.class, message = "Message not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable @ApiParam(name = "id", value = "Message Identification", example="1") Long id) {
        return ResponseEntity.ok(mapper.toDto(readMessageUseCase.read(id)));
    }

    @ApiOperation(value = "Delete a message notification")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Message excluded"),
            @ApiResponse(code = 400, response = ErrorDto.class, message = "Invalid ID in URL path"),
            @ApiResponse(code = 409, response = ErrorDto.class, message = "Message already sent, cannot remove"),
            @ApiResponse(code = 404, response = ErrorDto.class, message = "Message not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity delete(@PathVariable @ApiParam(name = "id", value = "Message Identification", example="1") Long id) {
        deleteMessageUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
