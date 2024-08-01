//package com.estore.spring_security_config;
//
//import com.estore.dto.LoginResponse;
//import com.estore.service.UserService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserService userService;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        LoginResponse userDetails = userService.findUserDetailsByUsername(username);
//        if(userDetails == null){
//            throw new UsernameNotFoundException("User Not Found");
//        }
//        return new CustomUserDetails(userDetails);
//    }
//}
