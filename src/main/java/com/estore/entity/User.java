package com.estore.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String role; //USER OR ADMIN OR PUBLIC
}
