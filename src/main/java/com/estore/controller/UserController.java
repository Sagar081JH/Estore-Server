package com.estore.controller;

import com.estore.dto.*;
import com.estore.entity.Address;
import com.estore.entity.Credentials;
import com.estore.entity.User;
import com.estore.exception.UserNotFoundException;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/{userId}")
    ResponseEntity<Optional<?>> getProfile(@PathVariable long userId) {
        Optional<User> user = userService.getMyDetails(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        throw new UserNotFoundException("User not found with id : "+userId);
        //return new ResponseEntity<>(Optional.of("Couldn't get : User Not Found!"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/name")
    ResponseEntity<Optional<?>> updateName(@RequestBody UpdateNameRequest updateNameRequest){
        Optional<User> updatedUser = Optional.ofNullable(userService.updateUserName(updateNameRequest));
        if(updatedUser.isPresent()){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update Name: User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/phone")
    ResponseEntity<Optional<?>> updatePhone(@RequestBody UpdatePhoneNumberRequest req){
        Optional<Credentials> updatedCreds = Optional.ofNullable(userService.updatePhone(req));
        if(updatedCreds.isPresent()){
            return new ResponseEntity<>(updatedCreds, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update Phone Number: User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/dob")
    ResponseEntity<Optional<?>> updateDob(@RequestBody UpdateDobRequest req){
        Optional<User> updatedUser = Optional.ofNullable(userService.updateDob(req));
        if(updatedUser.isPresent()){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update Date of Birth: User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/address")
    ResponseEntity<Optional<?>> updateAddress(@RequestBody UpdateAddressRequest req){
        Optional<Address> updatedAddress = Optional.ofNullable(userService.updateAddress(req));
        if(updatedAddress.isPresent()){
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update Address: User Not Found!"), HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/pwd")
    ResponseEntity<Optional<?>> updatePwd(@RequestBody UpdatePasswordRequest req){
        Optional<?> updatedCred = Optional.ofNullable(userService.updatePwd(req));
        if(updatedCred.isPresent()){
            return new ResponseEntity<>(updatedCred, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Update Address: User Not Found!"), HttpStatus.NOT_FOUND);
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
