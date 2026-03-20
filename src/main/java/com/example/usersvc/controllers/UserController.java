package com.example.usersvc.controllers;

import com.example.usersvc.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public User login() {
        // signup --
        // no need to hash password
        // store user as is in db
        // no email verification

        // login -
        // check user + password present in db and return user else throw error

        // logout - delete token 200  OK
        // else return 404


        return null;
        // create loginrequestdto - email, password
        // logoutrequestdto - token
        // singuprequestdto - email, password, name,

        // homework: login, signup, logout <ResponseEntity void>
    }
}
