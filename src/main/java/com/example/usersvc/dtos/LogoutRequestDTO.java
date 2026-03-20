package com.example.usersvc.dtos;

import com.example.usersvc.models.Token;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {
    @NotBlank(message = "Token cannot be blank!")
    private String tokenValue;
}
