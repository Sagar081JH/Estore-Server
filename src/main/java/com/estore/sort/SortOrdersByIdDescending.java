package com.estore.sort;

import com.estore.dto.OrdersByUserIdResponse;

import java.util.Comparator;

public class SortOrdersByIdDescending implements Comparator<OrdersByUserIdResponse> {
    @Override
    public int compare(OrdersByUserIdResponse o1, OrdersByUserIdResponse o2) {
        return (int) (o2.getOrder_id()- o1.getOrder_id());
    }
}
