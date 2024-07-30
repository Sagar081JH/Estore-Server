package com.estore.repository;

import com.estore.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends CrudRepository<Product,Long> {
}