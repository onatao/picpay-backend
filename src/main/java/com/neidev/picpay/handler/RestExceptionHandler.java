package com.neidev.picpay.handler;

import com.neidev.picpay.handler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> handlerUserCrdedentialsException(Exception e, WebRequest request) {
        var exception = new ExceptionResponse
                (LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerResourceNotFoundException(Exception e, WebRequest request) {
        var exception = new ExceptionResponse
                (LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public final ResponseEntity<ExceptionResponse> handlerInvalidTransactionException(Exception e, WebRequest request) {
        var exception = new ExceptionResponse
                (LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPayerException.class)
    public final ResponseEntity<ExceptionResponse> handlerInvalidPayerException(Exception e, WebRequest request) {
        var exception = new ExceptionResponse
                (LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotificationException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotificationException(Exception e, WebRequest request) {
        var exception = new ExceptionResponse
                (LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_GATEWAY);
    }

}
