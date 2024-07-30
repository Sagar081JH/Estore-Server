package com.estore.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long productId;
    String title;
    String info;
    long price;
    //Specs
    String color;
    String storage;
    String display;
    String ram_rom;
    String processor;
    String rearCamera;
    String frontCamera;
    String battery;
}

