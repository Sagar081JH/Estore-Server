package com.estore.service;

import com.estore.entity.Orders;
import com.estore.entity.Product;
import com.estore.entity.User;
import com.estore.dto.*;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<?> register(Registration registration);
    Optional<LoginResponse> login(LoginRequest userCred);

    Optional<User> getMyDetails(long id);
    Optional<User> removeUserById(long id);
    Optional<User> updateUser(long id,User user);
    Optional<List<CartItemResponse>> getCart(long userId);
    Optional<?> addCartItem(CartItemAddRequest cartItemAddRequest);
    Optional<?> priceSummaryTotalPrice(long userId);
    boolean removeCartItem(long cart_item_id);
    boolean removeCartItemsByUserId(long userId);

    List<Optional<Product>> getOrders();
    Optional<List<Orders>> addOrder(List<Orders> orders);
    boolean deleteOrder(long userId);

    List<OrdersByUserIdResponse> getOrderProductsByUserId(long userId);
}
