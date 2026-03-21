package com.example.usersvc.services;

import com.example.usersvc.dtos.LoginRequestDTO;
import com.example.usersvc.dtos.LogoutRequestDTO;
import com.example.usersvc.dtos.SignUpRequestDTO;
import com.example.usersvc.models.Token;
import com.example.usersvc.models.User;
import com.example.usersvc.repositories.TokenRepository;
import com.example.usersvc.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    UserService(UserRepository repo, TokenRepository tokenRepo, BCryptPasswordEncoder encoder) {
        this.userRepository = repo;
        this.tokenRepository = tokenRepo;
        this.bCryptPasswordEncoder = encoder;
    }

    public User addNewUser(String name, String email, String password) {
            String hashedPassword = bCryptPasswordEncoder.encode(password);
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setHashedPassword(hashedPassword);
            User savedUser = userRepository.save(user);
            return savedUser;
    }


    public Token loginUser(String email, String password) {
        log.info("----Login Request Received----");
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isEmpty()) return null;


        if(!bCryptPasswordEncoder.matches(password, userOptional.get().getHashedPassword())) {
            return null;
        }

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);


        // Create token --
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setUser(userOptional.get());
        token.setExpiryAt(expiryDate);

        // Generate a random 128 char token value
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        tokenRepository.save(token);
        return token;
    }


    public void logoutUser(String tokenValue) {
        log.info("----Logout Request Received----");
//        Optional<Token>
    }
}
