package com.example.usersvc.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    @NotBlank(message = "Name must not be blank!")
    private String name;
    @Email
    private String email;
    @NotBlank(message = "Password must not be blank!")
    @Size(min = 6, message = "Password must be at least 6 characters long!")
    private String password;
}
