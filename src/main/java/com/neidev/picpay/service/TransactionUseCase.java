package com.neidev.picpay.service;

import com.neidev.picpay.domain.core.transaction.json.TransactionForm;
import com.neidev.picpay.domain.core.transaction.json.TransactionResponse;
import com.neidev.picpay.domain.core.transaction.model.Transaction;

import java.util.List;

public interface TransactionUseCase {
    public TransactionResponse create(TransactionForm data);
    public List<TransactionResponse> findAll();
}
