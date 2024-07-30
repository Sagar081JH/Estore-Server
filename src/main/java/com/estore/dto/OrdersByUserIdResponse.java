package com.estore.dto;

import com.estore.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class OrdersByUserIdResponse {
    long order_id;
    Optional<Product> product;
}
