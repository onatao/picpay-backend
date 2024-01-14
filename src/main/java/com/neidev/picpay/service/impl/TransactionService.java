package com.neidev.picpay.service.impl;

import com.neidev.picpay.domain.core.transaction.json.TransactionForm;
import com.neidev.picpay.domain.core.transaction.json.TransactionResponse;
import com.neidev.picpay.domain.core.transaction.model.Transaction;
import com.neidev.picpay.domain.repository.TransactionRepository;
import com.neidev.picpay.handler.exception.InvalidTransactionException;
import com.neidev.picpay.infra.NotificationService;
import com.neidev.picpay.service.TransactionUseCase;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements TransactionUseCase {

    private final String AUTHORIZATION_SERVER = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final NotificationService notificationService;


    public TransactionService(TransactionRepository transactionRepository, UserService userService,
                              RestTemplate restTemplate, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public TransactionResponse create(TransactionForm data) {
        try {
            var payer = data.getPayer();
            var payee = data.getPayee();

            userService.isPayerValid(payer, data.getAmount());

            if(!isTransactionAuthorized())
                throw new InvalidTransactionException("Transaction not allowed.");

            var transaction = new Transaction();
            transaction.setPayer(payer);
            transaction.setPayee(payee);
            transaction.setAmount(data.getAmount());
            transaction.setMoment(LocalDateTime.now());

            payer.setBalance(payer.getBalance().subtract(transaction.getAmount()));
            payee.setBalance(payee.getBalance().add(transaction.getAmount()));
            transactionRepository.save(transaction);

            userService.updateBalance(payer);
            userService.updateBalance(payee);

            notificationService.sendNotification(payer, "Payment made successfully");
            notificationService.sendNotification(payee, "You've received a new payment");

            return transaction.toResponse();
        } catch (InvalidTransactionException e) {
            throw new InvalidTransactionException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> findAll() {
        return null;
    }

    boolean isTransactionAuthorized() {
        var response = restTemplate.getForEntity(AUTHORIZATION_SERVER, Map.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        return false;
    }
}
