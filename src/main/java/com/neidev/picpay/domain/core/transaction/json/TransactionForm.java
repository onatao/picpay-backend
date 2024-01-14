package com.neidev.picpay.domain.core.transaction.json;

import com.neidev.picpay.domain.core.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionForm {

    @NotNull
    private String id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime moment;

    @NotNull
    private User payer;
    @NotNull
    private User payee;

}
