package com.neidev.picpay.domain.core.transaction.json;

import com.neidev.picpay.domain.core.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
