package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePhoneNumberRequest {
    long userId;
    long credId;
    long phoneNo;
}
