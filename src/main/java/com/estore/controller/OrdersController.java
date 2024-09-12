package com.estore.controller;

import com.estore.entity.Orders;
import com.estore.entity.Product;
import com.estore.dto.OrdersByUserIdResponse;
import com.estore.service.UserService;
import com.estore.sort.SortOrdersByDate;
import com.estore.sort.SortOrdersByIdDescending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class OrdersController {
    @Autowired
    UserService userService;

    @GetMapping("/orders")
    ResponseEntity<?> getOrders(){
        List<Optional<Product>> ordersList= userService.getOrders();
        if(ordersList.isEmpty()){
            return new ResponseEntity<>("No order present!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        }
    }

    @GetMapping("/orders/{userId}")
    ResponseEntity<?> getOrdersByUserId(@PathVariable long userId){
        List<OrdersByUserIdResponse> ordersList= userService.getOrderProductsByUserId(userId);
        if(ordersList.isEmpty()){
            return new ResponseEntity<>("No order present!", HttpStatus.NOT_FOUND);
        }else{
           ordersList.sort(new SortOrdersByIdDescending());
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        }
    }

    @PostMapping("/orders")
    ResponseEntity<?> addOrders(@RequestBody List<Orders> orders){
        Optional<List<Orders>> order= userService.addOrder(orders);
        if(order.isEmpty()){
            return new ResponseEntity<>("No order present!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    @DeleteMapping("/orders/{order_id}")
    ResponseEntity<?> cancelOrder(@PathVariable long order_id){
        boolean isOrderDeleted= userService.deleteOrder(order_id);
        if(isOrderDeleted){
            return new ResponseEntity<>("Order Canceled !", HttpStatus.OK);

        }else{
            return new ResponseEntity<>("No order present!", HttpStatus.NOT_FOUND);
        }
    }
}
