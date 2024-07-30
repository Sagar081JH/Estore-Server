package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemAddRequest {
    long productId;
    long userId;
}
