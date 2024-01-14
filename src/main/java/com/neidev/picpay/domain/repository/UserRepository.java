package com.neidev.picpay.domain.repository;

import com.neidev.picpay.domain.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
