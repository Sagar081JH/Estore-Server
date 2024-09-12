package com.estore.sort;

import com.estore.dto.CartItemResponse;

import java.util.Comparator;

public class SortCartItemsByIdAscending implements Comparator<CartItemResponse> {
    @Override
    public int compare(CartItemResponse o1, CartItemResponse o2) {
        return (int) (o1.getCartItem().getCart_item_id()- o2.getCartItem().getCart_item_id());
    }
}
