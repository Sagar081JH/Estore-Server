package com.estore.repository;

import com.estore.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM cart_item WHERE user_id=?1",nativeQuery = true)
    List<Object[]> getCartItemsByUserId(long userId);

    @Query(value = "SELECT COUNT(*) FROM credentials WHERE email=?1",nativeQuery = true)
    int findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (user_id,first_name,last_name,role) values (?1,?2,?3,?4)",nativeQuery = true)
    void saveUser(long userIdInc,String firstName,String lastName,String role);


}
