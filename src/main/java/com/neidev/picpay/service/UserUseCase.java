package com.neidev.picpay.service;

import com.neidev.picpay.domain.core.user.json.UserForm;
import com.neidev.picpay.domain.core.user.json.UserResponse;

import java.util.List;

public interface UserUseCase {

    public UserResponse create(UserForm data);

    public List<UserResponse> findAll();
}
