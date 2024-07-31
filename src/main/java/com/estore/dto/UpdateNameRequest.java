package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateNameRequest {
    long userId;
    String firstName;
    String lastName;
}
