package com.estore.controller;

import com.estore.dto.LoginRequest;
import com.estore.dto.LoginResponse;
import com.estore.dto.Registration;
import com.estore.exception.LoginFailedException;
import com.estore.service.LoginService;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin()
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/login")
    ResponseEntity<Optional<?>> login(@RequestBody LoginRequest loginRequest){
        Optional<LoginResponse> response = userService.login(loginRequest);
        if(response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new LoginFailedException("Incorrect Username or Password !");
       // return new ResponseEntity<>(Optional.of("LoginController/login() : Couldn't login due to incorrect credentials!"), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    ResponseEntity<Optional<?>> addUser(@RequestBody Registration registration){
        registration.getCredentials().setPwd(bCryptPasswordEncoder.encode(registration.getCredentials().getPwd()));
        Optional<?> newUser = userService.register(registration);
        if(newUser.isPresent()){
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't add : Registration Failed!"), HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/forgotPassword")
    ResponseEntity<Optional<?>> forgotPwd(@RequestBody String email){
        Optional<?> resp = loginService.forgotPassword(email);
        if(resp.isPresent()){
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't forgot!"), HttpStatus.BAD_GATEWAY);
    }

//    @PostMapping("/login")
//    ResponseEntity<Optional<?>> login2(@RequestBody LoginRequest loginRequest){
//        Authentication authentication=authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(),loginRequest.getPwd()));
//        return new ResponseEntity<>(Optional.of(authentication), HttpStatus.OK);
//    }

}
