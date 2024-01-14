package com.neidev.picpay.domain.core.user.json;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UserResponse {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String document;
    @NotNull
    private BigDecimal balance;
}
