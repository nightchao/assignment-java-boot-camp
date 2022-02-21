package com.user.controller;

import com.exception.ExceptionModel;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.model.AddressResponse;
import com.user.repo.AddressRepository;
import com.user.repo.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("ค้นหาที่อยู่โดยส่ง userId = 22 แล้วได้ผลลัพธ์โดย address = test address")
    void case01() {
        // Arrange
        ScmUser user = new ScmUser(22, "user name");
        when(userRepository.findById(22)).thenReturn(Optional.of(user));

        Address address = new Address(11, 22);
        address.setAddress("test address");
        when(addressRepository.findAddress(22)).thenReturn(Optional.of(address));

        // Act
        AddressResponse result = testRestTemplate.getForObject("/user/22/address", AddressResponse.class);

        // Assert
        assertEquals("test address", result.getAddress());
    }

    @Test
    @DisplayName("ค้นหาที่อยู่โดยส่ง userId = 22 แล้วได้รับ JSON Object error กรณี User not found")
    void case02() {
        // Arrange
        when(userRepository.findById(22)).thenReturn(Optional.empty());

        Address address = new Address(11, 22);
        address.setAddress("test address");
        when(addressRepository.findAddress(22)).thenReturn(Optional.of(address));

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/user/22/address", ExceptionModel.class);

        // Assert
        String expectedMessage = "User id: 22 not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @DisplayName("ค้นหาที่อยู่โดยส่ง userId = 22 แล้วได้รับ JSON Object error กรณี Address not found")
    void case03() {
        // Arrange
        ScmUser user = new ScmUser(22, "user name");
        when(userRepository.findById(22)).thenReturn(Optional.of(user));

        when(addressRepository.findAddress(22)).thenReturn(Optional.empty());

        // Act
        ExceptionModel exception = testRestTemplate.getForObject("/user/22/address", ExceptionModel.class);

        // Assert
        String expectedMessage = "Address not found userId: 22";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertThat(exception.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));
    }
}