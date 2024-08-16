package com.estore.impl;

import com.estore.entity.Credentials;
import com.estore.repository.CredRepo;
import com.estore.repository.UserRepo;
import com.estore.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

@Service
@AllArgsConstructor
public class LoginImpl implements LoginService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    CredRepo credRepo;

    @Override
    public Optional<?> forgotPassword(String email) {
        //TODO
        return Optional.empty();
    }
}
