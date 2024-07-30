package com.estore.impl;

import com.estore.entity.Address;
import com.estore.entity.Credentials;
import com.estore.entity.Product;
import com.estore.entity.User;
import com.estore.dto.Registration;
import com.estore.repository.AddressRepo;
import com.estore.repository.CredRepo;
import com.estore.repository.ProductsRepo;
import com.estore.repository.UserRepo;
import com.estore.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminImpl implements AdminService {
    @Autowired
    UserRepo userRepo;
    CredRepo credRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    ProductsRepo productsRepo;

    @Override
    public Optional<List<User>> getUsers() {
        List<User> allUser = new ArrayList<>();
        try{
            userRepo.findAll().forEach(allUser::add);
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }
        return Optional.of(allUser);
    }

    @Override
    public Optional<?> registerAdminOrUser(Registration registration) {
        User user = registration.getUser();
        Address address= registration.getAddress();
        Credentials credentials=registration.getCredentials();

        int existingEmail = userRepo.findByEmail(credentials.getEmail());
        int existingPhoneNo = credRepo.findByPhoneNo(credentials.getPhoneNumber());

        try{
            if(existingEmail == 0 && existingPhoneNo == 0) {
                User savedUser=userRepo.save(user);
                credentials.setUser(savedUser);
                credRepo.save(credentials);
                address.setUser(savedUser);
                addressRepo.save(address);

                registration.setUser(savedUser);
                registration.setCredentials(credentials);
                registration.setAddress(address);
                return Optional.of(registration);
            }else{
                String error="";
                if(existingEmail != 0 && existingPhoneNo != 0){
                    return Optional.of("Email and Phone Number already exists !");
                }else{
                    if(existingEmail != 0){
                        error="Email already exists!";
                    }
                    if(existingPhoneNo !=0){
                        error="Phone Number already exists!";
                    }
                }
                return Optional.of(error);
            }
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> addNewProduct(Product product){
        Optional<Product> product1;
        if(!productsRepo.existsById(product.getProductId())){
            product1= Optional.of(productsRepo.save(product));
            return product1;
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getProducts(){
        List<Product> products = new ArrayList<>();
        try{
            productsRepo.findAll().forEach(products::add);
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }
        return Optional.of(products);
    }
}
