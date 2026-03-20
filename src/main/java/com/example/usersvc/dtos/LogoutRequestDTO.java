package com.example.usersvc.dtos;

import com.example.usersvc.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {
    private Token token;
}
