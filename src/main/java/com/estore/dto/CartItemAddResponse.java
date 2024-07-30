package com.estore.dto;

import com.estore.entity.CartItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemAddResponse {
    CartItem cartItem;
}
