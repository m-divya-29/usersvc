package com.example.usersvc.services;

import com.example.usersvc.dtos.LoginRequestDTO;
import com.example.usersvc.dtos.LogoutRequestDTO;
import com.example.usersvc.dtos.SignUpRequestDTO;
import com.example.usersvc.models.User;
import com.example.usersvc.repositories.TokenRepository;
import com.example.usersvc.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    UserService(UserRepository repo, TokenRepository tokenRepo) {
        this.userRepository = repo;
        this.tokenRepository = tokenRepo;
    }

    public User addNewUser(SignUpRequestDTO dto) {
        log.info("Requested to add a new user");
        User user =
                userRepository.findUserByEmail(dto.getEmail());

        if(user == null) {
            log.info("Adding new user");

            user = userRepository.saveAndFlush(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
        } else {
            // do nothing
            log.info("Existing user, do nothing");
            return user;
        }

        return user;
    }


    public User loginUser(LoginRequestDTO dto) {
        log.info("----Login Request Received----");
        User userExists =
                userRepository.findUserByEmailAndHashedPassword(dto.getEmail(), dto.getPassword());
        log.info("Login user: " + userExists);

        return userExists;
    }


    public void logoutUser(LogoutRequestDTO dto) {
        log.info("----Logout Request Received----");
        if(tokenRepository.findByValue(dto.getTokenValue()).isPresent()) {
            tokenRepository.deleteTokenByValue(dto.getTokenValue());
        } else {
            log.error("NO Token Found");
        }
    }
}
