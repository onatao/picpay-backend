package com.neidev.picpay.domain.core.transaction.json;

import com.neidev.picpay.domain.core.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    @NotNull
    private String id;
    @NotBlank
    private BigDecimal amount;
    @NotBlank
    private LocalDateTime moment;

    @NotNull
    private User payer;
    @NotNull
    private User payee;
}
