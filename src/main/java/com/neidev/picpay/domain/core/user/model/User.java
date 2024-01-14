package com.neidev.picpay.domain.core.user.model;

import com.neidev.picpay.domain.core.user.json.UserResponse;
import com.neidev.picpay.enums.UserCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "USER")
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false, unique = true)
    private String document;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passowrd;

    private UserCategory userCategory;

    public UserResponse toResponse() {
        return UserResponse.builder()
                .name(getName())
                .email(getEmail())
                .document(getDocument())
                .balance(getBalance())
                .userCategory(getUserCategory())
                .build();
    }
}
