package com.neidev.picpay.domain.core.user.json;

import com.neidev.picpay.enums.UserCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserResponse {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String document;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private UserCategory userCategory;
}
