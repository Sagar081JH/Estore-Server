package com.estore.dto;

import com.estore.entity.CartItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    long user_id;
    long product_id;
    CartItem cartItem;
}
