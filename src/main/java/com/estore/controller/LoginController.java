package com.estore.controller;

import com.estore.dto.LoginRequest;
import com.estore.dto.LoginResponse;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    ResponseEntity<Optional<?>> login(@RequestBody LoginRequest loginRequest){
        Optional<LoginResponse> response = userService.login(loginRequest);
        if(response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't login : incorrect credentials!"), HttpStatus.UNAUTHORIZED);
    }
}
