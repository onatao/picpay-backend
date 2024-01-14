package com.neidev.picpay.domain.core.transaction.model;

import com.neidev.picpay.domain.core.transaction.json.TransactionResponse;
import com.neidev.picpay.domain.core.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "TRANSACTION")
@Table(name = "TB_TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime moment;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    public TransactionResponse toResponse() {
        return TransactionResponse.builder()
                .id(getId())
                .amount(getAmount())
                .moment(getMoment())
                .payer(getPayer())
                .payee(getPayee())
                .build();
    }
}
