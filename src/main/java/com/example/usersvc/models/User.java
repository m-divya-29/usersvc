package com.example.usersvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.hashedPassword = password; // hash later
    }
}
