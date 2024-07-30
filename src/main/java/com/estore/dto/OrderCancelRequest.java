package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCancelRequest {
    long userId;
    long orderId;
}
