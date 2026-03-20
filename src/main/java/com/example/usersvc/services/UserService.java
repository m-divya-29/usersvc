package com.example.usersvc.services;

import com.example.usersvc.dtos.LoginRequestDTO;
import com.example.usersvc.dtos.LogoutRequestDTO;
import com.example.usersvc.dtos.SignUpRequestDTO;
import com.example.usersvc.models.User;
import com.example.usersvc.repositories.TokenRepository;
import com.example.usersvc.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    UserService(UserRepository repo, TokenRepository tokenRepo) {
        this.userRepository = repo;
        this.tokenRepository = tokenRepo;
    }

    public User addNewUser(SignUpRequestDTO dto) {
        User user =
                userRepository.findUserByEmail(dto.getEmail());

        if(user == null) {
            user = userRepository.saveAndFlush(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
        } else {
            // do nothing
            return user;
        }

        return user;
    }


    public User loginUser(LoginRequestDTO dto) {
        User userExists =
                userRepository.findUserByEmailAndHashedPassword(dto.getEmail(), dto.getPassword());
        return userExists;
    }


    public void logoutUser(LogoutRequestDTO dto) {
        if(tokenRepository.findByValue(dto.getToken().getValue())) {
            tokenRepository.deleteTokenById(dto.getToken().getId());
        } else {
            System.out.println("NO Token Found");
        }
    }
}
