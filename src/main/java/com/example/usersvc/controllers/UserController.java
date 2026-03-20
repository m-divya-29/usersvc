package com.example.usersvc.controllers;

import com.example.usersvc.dtos.LoginRequestDTO;
import com.example.usersvc.dtos.LogoutRequestDTO;
import com.example.usersvc.dtos.SignUpRequestDTO;
import com.example.usersvc.models.User;
import com.example.usersvc.repositories.UserRepository;
import com.example.usersvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginRequestDTO dto) {
        return userService.loginUser(dto);
    }

    @PostMapping("/signUp")
    public User signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return userService.addNewUser(signUpRequestDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequestDTO dto) {
        userService.logoutUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
