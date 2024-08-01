//package com.estore.spring_security_config;
//
//import com.estore.dto.LoginResponse;
//import com.estore.entity.Address;
//import com.estore.entity.Credentials;
//import com.estore.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final LoginResponse userDetails;
//
//    public CustomUserDetails(LoginResponse response){
//        super();
//        this.userDetails=response;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority(userDetails.getUser().getRole()));
//    }
//
//    @Override
//    public String getPassword() {
//        return userDetails.getCredentials().getPwd();
//    }
//
//    @Override
//    public String getUsername() {
//        return userDetails.getCredentials().getEmail();
//    }
//
//    public User getUser(){
//        return userDetails.getUser();
//    }
//    public Address getAddress(){
//        return userDetails.getAddress();
//    }
//    public Credentials getCredentials(){
//        return userDetails.getCredentials();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
