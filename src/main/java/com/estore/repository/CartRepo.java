package com.estore.repository;

import com.estore.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends CrudRepository<CartItem,Long> {

    @Query(value = "select * from cart_item where user_id=?1",nativeQuery = true)
    List<CartItem> findCartItemsByUserId(long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_item where user_id=?1",nativeQuery = true)
    void deleteByUserId(long userId);


}