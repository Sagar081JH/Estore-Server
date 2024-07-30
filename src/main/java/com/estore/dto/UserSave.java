package com.estore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSave {
    String firstName;
    String lastName;
    String role;
}
