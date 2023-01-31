package com.security.SpringSecurity.controller;

import com.security.SpringSecurity.Dto.UserAuthorityDto;
import com.security.SpringSecurity.Entity.User;
import com.security.SpringSecurity.Exception.ResourceNotFoundException;
import com.security.SpringSecurity.Exception.UserException;
import com.security.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller includes all the api related to signup and login
 * All api starts with "/user"
 * Communicates with Service layer (UserService) for business logic
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService cSer;

    // @POST method to register by taking User = /user/signup
    // return UserAuthorityDto with a CREATED status
    @PostMapping("/signup")
    public ResponseEntity<UserAuthorityDto> signupHandler (@Valid @RequestBody User user) throws UserException {
        UserAuthorityDto savedUser = cSer.signup(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // @POST method to log in = /user/login
    @PostMapping("/login")
    public ResponseEntity<String> loginHandler(Authentication authentication) throws ResourceNotFoundException {
        String message = cSer.login(authentication);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
