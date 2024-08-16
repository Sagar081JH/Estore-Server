package com.estore.service;

import java.util.Optional;

public interface LoginService {
    Optional<?> forgotPassword(String email);
}
