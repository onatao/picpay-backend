package com.neidev.picpay.domain.core.user.json;

import com.neidev.picpay.enums.UserCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UserForm {

    @NotNull
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    private BigDecimal balance;
    @NotBlank
    private String document;
    @Email
    private String email;
    @NotBlank
    private String passowrd;

    @NotNull
    private UserCategory userCategory;
}
