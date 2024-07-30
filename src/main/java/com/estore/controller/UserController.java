package com.estore.controller;

import com.estore.entity.User;
import com.estore.dto.Registration;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<Optional<?>> getProfile(@PathVariable long userId){
        Optional<User> user = userService.getMyDetails(userId);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't get : User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register")
    ResponseEntity<Optional<?>> addUser(@RequestBody Registration registration){
        Optional<?> newUser = userService.register(registration);
        if(newUser.isPresent()){
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't add : Registration Failed!"), HttpStatus.BAD_GATEWAY);
    }
    @PutMapping("/{userId}")
    ResponseEntity<Optional<?>> updateProfile(@PathVariable long userId,@RequestBody User user){
        Optional<User> updatedUser = userService.updateUser(userId,user);
        if(updatedUser.isPresent()){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update : User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{userId}")
    ResponseEntity<Optional<?>> deleteAccount(@PathVariable long userId){
        Optional<User> newUser = userService.removeUserById(userId);
        if(newUser.isPresent()){
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Remove : User Not Found!"), HttpStatus.NOT_FOUND);
    }

}
