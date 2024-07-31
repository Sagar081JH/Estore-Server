package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAddressRequest {
    long userId;
    long addressId;
    String area;
    String city;
    String state;
    String country;
    int pinCode;
}
