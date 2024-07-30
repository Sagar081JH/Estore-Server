package com.estore.controller;


import com.estore.dto.CartItemAddRequest;
import com.estore.dto.CartItemResponse;
import com.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CartController {
    @Autowired
    UserService userService;

    @GetMapping("/cart/{userId}")
    ResponseEntity<?> getCartItems(@PathVariable long userId){
        Optional<List<CartItemResponse>> cartItems = userService.getCart(userId);
        if(cartItems.isPresent()){
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't retrieve cart",HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/cart/add")
    ResponseEntity<?> addCartItem(@RequestBody CartItemAddRequest cartItemAddRequest){
        Optional<?> cartItem1 = userService.addCartItem(cartItemAddRequest);
        if(cartItem1.isPresent()){
            return new ResponseEntity<>(cartItem1, HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't add!",HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/cart/total_price/{userId}")
    ResponseEntity<?> priceSummaryTotalPrice(@PathVariable long userId){
        Optional<?> totalPrice = userService.priceSummaryTotalPrice(userId);
        if(totalPrice.isPresent()){
            return new ResponseEntity<>(totalPrice, HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't add!",HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping("/cart/{cart_item_id}")
    ResponseEntity<?> removeCartItem(@PathVariable long cart_item_id){
        boolean isRemoved = userService.removeCartItem(cart_item_id);
        if(isRemoved){
            return new ResponseEntity<>("Removed Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't remove!",HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping("/cart/delete/{userId}")
    ResponseEntity<?> removeCartItemsByUserId(@PathVariable long userId){
        boolean isRemoved = userService.removeCartItemsByUserId(userId);
        if(isRemoved){
            return new ResponseEntity<>("CartItems removed Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't remove!",HttpStatus.BAD_GATEWAY);
    }
}
