package com.estore;

import com.estore.dto.LoginResponse;
import com.estore.dto.Registration;
import com.estore.entity.Address;
import com.estore.entity.Credentials;
import com.estore.entity.User;
import com.estore.impl.UserImpl;
import com.estore.repository.UserRepo;
import com.estore.service.UserService;
import com.mysql.cj.log.Log;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Configuration
@ExtendWith(MockitoExtension.class)
public class UserImplTests {
    private final User mockUser =User
            .builder()
            .dateOfBirth("06/07/1997")
            .role("ADMIN")
            .firstName("Sagar")
            .lastName("Ghumare")
            .gender("Male")
            .build();;
    private final Credentials mockCredentials =  Credentials
            .builder()
            .email("sagar123@gmail.com")
            .phoneNumber(9599445566L)
            .pwd("Sagar@123")
            .build();;
    private final Address mockAaddress = Address
            .builder()
            .area("Beedsangvi")
            .city("Ashti")
            .state("Maharashtra")
            .country("India")
            .pinCode(414203)
            .build();;
    private final LoginResponse mockResponse = LoginResponse
            .builder()
            .user(mockUser)
            .credentials(mockCredentials)
            .address(mockAaddress)
            .build();;

    @Mock
    private UserRepo mockRepository;

    @InjectMocks
    UserImpl assetService;


    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(UserImplTests.class);
    }
    @Test
    public void whenLogin_thenReturnLoginResponse() {
        // given
        //LoginResponse response = userService.findUserDetailsByUsername(test_credentials.getEmail());

        // when
        Mockito.when(assetService.findUserDetailsByUsername(mockCredentials.getEmail()))
                .thenReturn(mockResponse);
        // then
       Mockito.verify(mockCredentials.getEmail()).contains(mockResponse.getCredentials().getEmail());
    }
}
