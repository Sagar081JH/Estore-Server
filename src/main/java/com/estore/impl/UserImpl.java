package com.estore.impl;

import com.estore.entity.*;
import com.estore.dto.*;
import com.estore.repository.*;
import com.estore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    CredRepo credRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    ProductsRepo productsRepo;


    @Override
    public Optional<?> register(Registration registration) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = registration.getUser();
        Address address= registration.getAddress();
        Credentials credentials=registration.getCredentials();
        user.setRole(String.valueOf(ROLE.USER));

        int existingEmail = userRepo.findByEmail(credentials.getEmail());
        int existingPhoneNo = credRepo.findByPhoneNo(credentials.getPhoneNumber());

        try{
            if(existingEmail == 0 && existingPhoneNo == 0) {
                User savedUser=userRepo.save(user);
                credentials.setUser(savedUser);
//                credentials.setPwd(bCryptPasswordEncoder.encode(credentials.getPwd()));
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
    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        try{
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
           Optional<Credentials> credentials = Optional.empty();
           if(loginRequest.getUsername().contains("@")){
               credentials= Optional.ofNullable(credRepo.findByEmail(loginRequest.getUsername()));
           }
          else{
               credentials= Optional.ofNullable(credRepo.findByPhone(Long.parseLong(loginRequest.getUsername())));
           }
          boolean isPwdValid=false;

          if(credentials.isPresent()) {
              isPwdValid = bCryptPasswordEncoder.matches(loginRequest.getPassword(), credentials.get().getPwd());
          }

            if(isPwdValid){
                long id=credentials.get().getUser().getUserId();
                Credentials credentials1=credRepo.findByIdUserId(id);
                credentials1.setPwd(loginRequest.getPassword());
               LoginResponse loginResponse = LoginResponse.builder()
                       .user(userRepo.findById(id).get())
                       .address(addressRepo.findByIdUserId(id))
                       .credentials(credentials1)
                       .build();
               return Optional.ofNullable(loginResponse);
           }
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage()+" : "+e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getMyDetails(long id) {
        try{
            if(userRepo.existsById(id)){
                return userRepo.findById(id);
            }
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }

        return Optional.empty();
    }



    @Override
    public Optional<User> removeUserById(long id) {
        try{
            if(userRepo.existsById(id)){
                Optional<User> newUser = getMyDetails(id);
                userRepo.deleteById(id);
                return newUser;
            }
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<CartItemResponse>> getCart(long userId){
        try {
            List<CartItemResponse> cartItemResponses = new ArrayList<>();
            List<CartItem> cartItems = cartRepo.findCartItemsByUserId(userId);
            cartItems.forEach(cartItem -> {
                cartItemResponses.add(CartItemResponse.builder()
                        .user_id(userId)
                        .product_id(cartItem.getProduct().getProductId())
                        .cartItem(cartItem)
                        .build());
            });
            return Optional.of(cartItemResponses);
        }catch (Exception e){
            System.out.println(e+":"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<?> addCartItem(CartItemAddRequest cartItemAddRequest){

        if(productsRepo.existsById(cartItemAddRequest.getProductId())){
            Optional<User> user= userRepo.findById(cartItemAddRequest.getUserId());
            Optional<Product> product = productsRepo.findById(cartItemAddRequest.getProductId());

            if(product.isPresent() && user.isPresent()) {

                Product p1 = product.get();
                CartItem cartItem = CartItem.builder()
                        .title(p1.getTitle())
                        .info(p1.getInfo())
                        .price(p1.getPrice())
                        .color(p1.getColor())
                        .battery(p1.getBattery())
                        .display(p1.getDisplay())
                        .frontCamera(p1.getFrontCamera())
                        .rearCamera(p1.getRearCamera())
                        .processor(p1.getProcessor())
                        .ram_rom(p1.getRam_rom())
                        .storage(p1.getStorage())
                        .user(user.get())
                        .product(p1)
                        .build();

                return Optional.of(cartRepo.save(cartItem));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public  Optional<?> priceSummaryTotalPrice(long userId){
        if(userRepo.existsById(userId)){
            long totalPrice=0;
            List<CartItem> cartItems = cartRepo.findCartItemsByUserId(userId);
            if(!cartItems.isEmpty()){
                for (CartItem cartItem : cartItems) {
                    long price = cartItem.getPrice();
                    totalPrice = totalPrice + price;
                }
                return Optional.of(getIndianCurrencyFormat(Long.toString(totalPrice)));
            }
            return Optional.of("Empty cart!");
        }
        return Optional.of("User not found!");
    }

    public String getIndianCurrencyFormat(String amount) {
        StringBuilder stringBuilder = new StringBuilder();
        char amountArray[] = amount.toCharArray();
        int a = 0, b = 0;
        for (int i = amountArray.length - 1; i >= 0; i--) {
            if (a < 3) {
                stringBuilder.append(amountArray[i]);
                a++;
            } else if (b < 2) {
                if (b == 0) {
                    stringBuilder.append(",");
                    stringBuilder.append(amountArray[i]);
                    b++;
                } else {
                    stringBuilder.append(amountArray[i]);
                    b = 0;
                }
            }
        }
        return stringBuilder.reverse().toString();
    }


    @Override
    public boolean removeCartItem(long cart_item_id) {
        if(cartRepo.existsById(cart_item_id)){
            cartRepo.deleteById(cart_item_id);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCartItemsByUserId(long userId) {
        try{
            if(userRepo.existsById(userId)){
                cartRepo.deleteByUserId(userId);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("removeCartItemsByUserId : "+e.getMessage());
        }
        return false;
    }

    @Override
    public List<Optional<Product>> getOrders() {
        try{
            List<Orders> ordersList = ordersRepo.findAll();
            List<Optional<Product>> productList = new ArrayList<>();
            ordersList.forEach(order ->{
                productList.add(productsRepo.findById(order.getProduct_id()));
            });
            return productList;
        }catch (Exception e){
            System.out.println("GetOrders:"+e.getMessage());
        }
        return List.of();
    }

    @Override
    public Optional<List<Orders>> addOrder(List<Orders> orders) {
        try{
            List<Orders> savedOrders = new ArrayList<>();
            orders.forEach(order -> {
                if(userRepo.existsById(order.getUser_id()) && productsRepo.existsById(order.getProduct_id())){
                    savedOrders.add(ordersRepo.save(order));
                }
            });
            return Optional.of(savedOrders);
        }catch (Exception e){
            System.out.println("addOrder : "+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteOrder(long orderId) {
        try{
            ordersRepo.deleteById(orderId);
            return true;
        }catch (Exception e){
            System.out.println("CancelOrder:"+e.getMessage());
        }
        return false;
    }

    @Override
    public List<OrdersByUserIdResponse> getOrderProductsByUserId(long userId) {
        try{
            List<OrdersByUserIdResponse> response = new ArrayList<>();
            if(userRepo.existsById(userId)){
                List<Orders> ordersByUserId = ordersRepo.findByUserId(userId);
                ordersByUserId.forEach(order ->{
                    response.add(OrdersByUserIdResponse.builder()
                            .order_id(order.getOrder_id())
                            .product(productsRepo.findById(order.getProduct_id()))
                            .orderDate(order.getOrderDate())
                            .build());
                });
            }
            return response;
        }catch (Exception e){
            System.out.println("getOrdersByUserId:"+e.getMessage());
        }
        return List.of();
    }

    @Override
    public User updateUserName(UpdateNameRequest updateNameRequest) {
       try {
           if(userRepo.existsById(updateNameRequest.getUserId())){
               Optional<User> user = userRepo.findById(updateNameRequest.getUserId());
               if(user.isPresent()){
                   user.get().setFirstName(updateNameRequest.getFirstName());
                   user.get().setLastName(updateNameRequest.getLastName());
                  return userRepo.save(user.get());
               }
               return null;
           }
       }catch (Exception e){
           System.out.println("updateUserName:"+e.getMessage());
       }
        return null;
    }

    @Override
    public Credentials updatePhone(UpdatePhoneNumberRequest req) {
        try {
            if(userRepo.existsById(req.getUserId())){
                Optional<Credentials> cred = credRepo.findById(req.getCredId());
                if(cred.isPresent()){
                    cred.get().setPhoneNumber(req.getPhoneNo());
                    return credRepo.save(cred.get());
                }
                return null;
            }
        }catch (Exception e){
            System.out.println("update phone number:"+e.getMessage());
        }
        return null;
    }

    @Override
    public User updateDob(UpdateDobRequest req) {
        try {
            if(userRepo.existsById(req.getUserId())){
                Optional<User> user = userRepo.findById(req.getUserId());
                if(user.isPresent()){
                    user.get().setDateOfBirth(req.getDateOfBirth());
                    return userRepo.save(user.get());
                }
                return null;
            }
        }catch (Exception e){
            System.out.println("update-Dob:"+e.getMessage());
        }
        return null;
    }

    @Override
    public Address updateAddress(UpdateAddressRequest req) {
        try {
            if(userRepo.existsById(req.getUserId())){
                Optional<Address> address = addressRepo.findById(req.getAddressId());
                if(address.isPresent()){
                    address.get().setArea(req.getArea());
                    address.get().setCity(req.getCity());
                    address.get().setState(req.getState());
                    address.get().setCountry(req.getCountry());
                    address.get().setPinCode(req.getPinCode());
                    return addressRepo.save(address.get());
                }
                return null;
            }
        }catch (Exception e){
            System.out.println("updateUserName:"+e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<?> updatePwd(UpdatePasswordRequest req) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            if(userRepo.existsById(req.getUserId())){
                Optional<Credentials> cred = credRepo.findById(req.getCredId());
                if(cred.isPresent()){
                    boolean isOldPwdValid = bCryptPasswordEncoder.matches(req.getOldPwd(),cred.get().getPwd());
                    if(isOldPwdValid){
                        cred.get().setPwd(bCryptPasswordEncoder.encode(req.getNewPwd()));
                        Credentials newCred =credRepo.save(cred.get());
                        newCred.setPwd(req.getNewPwd());
                        return Optional.of(newCred);
                    }else{
                        return Optional.of("Incorrect old password!");
                    }
                }
                return Optional.of("Credentials not found with given cred Id!");
            }
            return Optional.of("User not found with given user Id!");
        }catch (Exception e){
            System.out.println("update phone number:"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public LoginResponse findUserDetailsByUsername(String username) {
        try {
            Credentials credentials;
            if(username.contains("@")){
                credentials = credRepo.findByEmail(username);
            }else{
                credentials=credRepo.findByPhone(Long.parseLong(username));
            }
            if(credentials !=null){
                Optional<User> user= userRepo.findById(credentials.getUser().getUserId());
                Address address=addressRepo.findByUserId(user.get().getUserId());
                return LoginResponse.builder()
                        .user(user.get())
                        .credentials(credentials)
                        .address(address)
                        .build();
            }
            return null;
        }catch (Exception e){
            System.out.println("Find-By-Username:"+e.getMessage());
        }
        return null;
    }
}
