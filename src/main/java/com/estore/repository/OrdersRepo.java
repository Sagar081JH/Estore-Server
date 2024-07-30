package com.estore.repository;

import com.estore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Long> {
    @Query(value = "select * from user_orders where user_id=?1",nativeQuery = true)
    List<Orders> findByUserId(long userId);
}
