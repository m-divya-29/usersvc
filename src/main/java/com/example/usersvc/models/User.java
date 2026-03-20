package com.example.usersvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany
    private List<Role> roles;

    private boolean isEmailVerified;
    // 1 user can have many tokens -> multiple device login
    @OneToMany
    private List<Token> tokens;
}
