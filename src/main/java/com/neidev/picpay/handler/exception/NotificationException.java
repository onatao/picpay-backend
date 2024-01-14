package com.neidev.picpay.handler.exception;

import com.neidev.picpay.infra.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class NotificationException extends RuntimeException{
    public NotificationException(String message) {
        super(message);
    }
}
