package com.neidev.picpay.controller;

import com.neidev.picpay.domain.core.transaction.json.TransactionForm;
import com.neidev.picpay.domain.core.transaction.json.TransactionResponse;
import com.neidev.picpay.service.impl.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> registerTransaction(@RequestBody @Valid TransactionForm data) {
        return new ResponseEntity<>(transactionService.create(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> registerTransaction() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }
}
