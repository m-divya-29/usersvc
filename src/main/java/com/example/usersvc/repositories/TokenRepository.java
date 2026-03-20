package com.example.usersvc.repositories;

import com.example.usersvc.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean findByValue(String tokenValue);

    void deleteTokenById(Long id);
}
