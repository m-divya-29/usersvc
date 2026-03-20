package com.example.usersvc.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @Email(message = "Email not valid!")
    private String email;
    @Size(min = 6, message = "Your password is at least 6 characters long!")
    private String password;
}
