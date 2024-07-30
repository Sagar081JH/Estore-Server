package com.estore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    long user_id;
    long product_id;
}
