package com.example.usersvc.controllers;

import com.example.usersvc.dtos.LoginRequestDTO;
import com.example.usersvc.dtos.LogoutRequestDTO;
import com.example.usersvc.dtos.SignUpRequestDTO;
import com.example.usersvc.models.Token;
import com.example.usersvc.models.User;
import com.example.usersvc.repositories.UserRepository;
import com.example.usersvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Token login(@Valid @RequestBody LoginRequestDTO dto) {
        log.info("Login request received");
        return userService.loginUser(dto.getEmail(), dto.getPassword());
    }

    @PostMapping("/signup")
    public User signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        log.info("Signup request received");
        return userService.addNewUser(signUpRequestDTO.getName(),
                signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequestDTO dto) {
        log.info("Logout request received");
        userService.logoutUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
