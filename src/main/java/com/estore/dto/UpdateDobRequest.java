package com.estore.dto;

import com.estore.entity.Address;
import com.estore.entity.Credentials;
import com.estore.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDobRequest {
    long userId;
    String dateOfBirth;
}
