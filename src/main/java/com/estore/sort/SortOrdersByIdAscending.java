package com.estore.sort;

import com.estore.dto.OrdersByUserIdResponse;

import java.util.Comparator;

public class SortOrdersByIdAscending implements Comparator<OrdersByUserIdResponse> {
    @Override
    public int compare(OrdersByUserIdResponse o1, OrdersByUserIdResponse o2) {
        return (int) (o1.getOrder_id()- o2.getOrder_id());
    }
}
