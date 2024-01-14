package com.neidev.picpay.service.impl;

import com.neidev.picpay.domain.core.user.json.UserForm;
import com.neidev.picpay.domain.core.user.json.UserResponse;
import com.neidev.picpay.domain.core.user.model.User;
import com.neidev.picpay.domain.repository.UserRepository;
import com.neidev.picpay.enums.UserCategory;
import com.neidev.picpay.handler.exception.InvalidPayerException;
import com.neidev.picpay.handler.exception.ResourceNotFoundException;
import com.neidev.picpay.handler.exception.UserCredentialsException;
import com.neidev.picpay.service.UserUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponse create(UserForm data) {
        try {
            var email = data.getEmail();
            var document = data.getDocument();

            if (!isEmailValid(email))
                throw new UserCredentialsException("User email already in use.");

            if (!isDocumentValid(document))
                throw new UserCredentialsException("User document already in use.");

            var entity = userRepository.save(data.toEntity());

            return entity.toResponse();
        } catch (UserCredentialsException e) {
            throw new UserCredentialsException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
       try {
           var userList = userRepository.findAll();

           if(userList.isEmpty())
               throw new ResourceNotFoundException("There are no registered users.");

           return userList.stream().map(User::toResponse).toList();
       } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException(e.getMessage());
       }
    }

    boolean isEmailValid(String email) {
        var result = userRepository.findByEmail(email);
        return result.isEmpty();
    }

    boolean isDocumentValid(String document) {
        var result = userRepository.findByDocument(document);
        return result.isEmpty();
    }

    public boolean isPayerValid(User payer, BigDecimal amount) {
        try {
            if(!(payer.getUserCategory() == UserCategory.COMMON))
                throw new InvalidPayerException("Payer doesn't have permission to proceed.");

            if(payer.getBalance().compareTo(amount) < 0)
                throw new InvalidPayerException("Operation cancelled, insufficient funds.");

            return true;
        } catch (InvalidPayerException e) {
            throw new InvalidPayerException(e.getMessage());
        }
    }

    public void updateBalance(User user) {
        userRepository.save(user);
    }
}
