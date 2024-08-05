package com.estore.controller;


import com.estore.entity.Product;
import com.estore.entity.User;
import com.estore.dto.Registration;
import com.estore.service.AdminService;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    ResponseEntity<Optional<?>> getUsers(){
        Optional<List<User>> users = adminService.getUsers();
        if(users.isPresent()){
            return new ResponseEntity<>(users, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't show : User list is empty!"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products")
    ResponseEntity<?> getProducts(){
        Optional<List<Product>> products = adminService.getProducts();
        if(products.isPresent()){
            return new ResponseEntity<>(products, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Couldn't show : No products available!", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/admin/register")
    ResponseEntity<Optional<?>> addUserOrAdmin(@RequestBody Registration registration){
        Optional<?> registration1 = adminService.registerAdminOrUser(registration);
        if(registration1.isPresent()){
            return new ResponseEntity<>(registration1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't add : Registration Failed!"), HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/products")
    ResponseEntity<Optional<?>> addProduct(@RequestBody Product product){
        Optional<Product> newProduct = adminService.addProduct(product);
        if(newProduct.isPresent()){
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't add : Registration Failed!"), HttpStatus.BAD_GATEWAY);
    }
    @PutMapping("/products/{pid}")
    ResponseEntity<Optional<?>> editProduct(@PathVariable long pid,@RequestBody Product product){
        Optional<?> newProduct = adminService.editProduct(pid,product);
        if(newProduct.isPresent()){
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't add : Registration Failed!"), HttpStatus.BAD_GATEWAY);
    }
    @DeleteMapping("/products/{pid}")
    ResponseEntity<Optional<?>> deleteProduct(@PathVariable long pid){
        Optional<Boolean> newProduct = adminService.deleteProduct(pid);
        if(newProduct.isPresent()){
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Optional.of("Couldn't delete !"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/{userId}")
    ResponseEntity<Optional<?>> deleteAccount(@PathVariable long userId){
        Optional<User> newUser = userService.removeUserById(userId);
        if(newUser.isPresent()){
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.of("Couldn't Remove : User Not Found!"), HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/admin/{userId}")
//    ResponseEntity<Optional<?>> updateUser(@PathVariable long userId,@RequestBody User user){
//        Optional<User> updatedUser = userService.updateUser(userId,user);
//        if(updatedUser.isPresent()){
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(Optional.of("Couldn't Update : User Not Found!"), HttpStatus.NOT_FOUND);
//    }
}
