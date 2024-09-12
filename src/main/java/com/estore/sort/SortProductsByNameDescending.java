package com.estore.sort;

import com.estore.entity.Product;

import java.util.Comparator;
public class SortProductsByNameDescending implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getTitle().compareTo(o1.getTitle());
    }
}
