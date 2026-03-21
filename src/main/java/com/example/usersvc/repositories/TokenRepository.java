package com.example.usersvc.repositories;

import com.example.usersvc.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String tokenValue);

    void deleteTokenByValue(String tokenValue);

    Optional<Token> findByValueAndIsDeleted(String tokenValue, boolean isDeleted);
}
