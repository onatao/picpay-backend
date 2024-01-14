package com.neidev.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidPayerException extends RuntimeException {
    public InvalidPayerException(String message) {
        super(message);
    }
}
