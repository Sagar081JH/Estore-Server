package com.estore.repository;

import com.estore.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends CrudRepository<Address,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into address (address_id,user_id,area,city,state,country,pin_code) values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void saveAddress(long addressIdInc,long userIdInc,String area,String city,String state,String country,long pincode);

    @Query(value = "select * from address where user_id=?1",nativeQuery = true)
    Address findByIdUserId(long userId);

    @Query(value = "select * from address where user_id=?1",nativeQuery = true)
    Address findByUserId(long userId);
}

