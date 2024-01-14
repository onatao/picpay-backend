package com.neidev.picpay.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserCredentialsException extends RuntimeException {
    public UserCredentialsException(String message) {
        super(message);
    }
}
