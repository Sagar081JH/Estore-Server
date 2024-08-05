package com.estore.service;

import com.estore.entity.Product;
import com.estore.entity.User;
import com.estore.dto.Registration;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<List<User>> getUsers();
    Optional<?> registerAdminOrUser(Registration registration);
    Optional<Product> addProduct(Product product);
    Optional<List<Product>> getProducts();
    Optional<Boolean> deleteProduct(long productId);
    Optional<?> editProduct(long productId,Product editedProduct);
}
