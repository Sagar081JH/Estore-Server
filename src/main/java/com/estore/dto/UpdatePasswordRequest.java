package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePasswordRequest {
    long userId;
    long credId;
    String oldPwd;
    String newPwd;
}
