package com.example.usersvc.repositories;

import com.example.usersvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndHashedPassword(String email, String password);

    User findUserByEmail(String email);
}
