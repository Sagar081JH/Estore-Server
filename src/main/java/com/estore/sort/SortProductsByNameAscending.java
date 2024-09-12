package com.estore.sort;

import com.estore.entity.Product;

import java.util.Comparator;

public class SortProductsByNameAscending implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
}
