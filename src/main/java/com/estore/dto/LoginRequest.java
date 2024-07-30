package com.estore.dto;

import lombok.Data;

@Data
public class LoginRequest {
    long phoneNo;
    String email;
    String pwd;
}
