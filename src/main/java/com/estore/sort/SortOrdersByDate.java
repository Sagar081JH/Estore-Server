package com.estore.sort;

import com.estore.dto.OrdersByUserIdResponse;

import java.util.Comparator;

public class SortOrdersByDate implements Comparator<OrdersByUserIdResponse> {
    @Override
    public int compare(OrdersByUserIdResponse o1, OrdersByUserIdResponse o2) {
        return o1.getOrderDate().compareTo(o2.getOrderDate());
    }
}
