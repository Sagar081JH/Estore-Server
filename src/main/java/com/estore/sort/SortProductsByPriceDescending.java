package com.estore.sort;

import com.estore.entity.Product;

import java.util.Comparator;
    public class SortProductsByPriceDescending implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            Integer o1Price = Integer.parseInt(String.valueOf(o1.getPrice()));
            Integer o2Price = Integer.parseInt(String.valueOf(o2.getPrice()));
            return o2Price.compareTo(o1Price);
        }
    }
