package com.neidev.picpay.handler;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
