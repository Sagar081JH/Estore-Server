package com.estore.repository;

import com.estore.entity.Credentials;
import com.estore.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredRepo extends CrudRepository<Credentials,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into credentials (cred_id,user_id,phone_number,email,pwd) values (?1,?2,?3,?4,?5)",nativeQuery = true)
    void saveCreds(long credIdInc,long userIdInc,long phoneNumber,String email,String pwd);

    @Query(value = "SELECT user_id FROM credentials WHERE phone_number=?1 and pwd=?3 or email=?2 and pwd=?3",nativeQuery = true)
    long loginByPhoneOrEmailAndPwd(long phoneNo, String email, String pwd);

    @Query(value = "SELECT * FROM user WHERE cred_id=?1",nativeQuery = true)
    User getUserByCredId(long credId);

    @Query(value = "SELECT COUNT(*) FROM credentials WHERE phone_number=?1",nativeQuery = true)
    int findByPhoneNo(long phoneNo);

    @Query(value = "select * from credentials where user_id=?1",nativeQuery = true)
    Credentials findByIdUserId(long userId);

    @Query(value = "select * from credentials where email=?1",nativeQuery = true)
    Credentials findByEmail(String email);

    @Query(value = "select * from credentials where phone_number=?1",nativeQuery = true)
    Credentials findByPhone(long phone);
}

