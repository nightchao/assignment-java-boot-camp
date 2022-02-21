package com.user.service;

import com.exception.ProductNotFoundException;
import com.exception.UserNotFoundException;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.repo.AddressRepository;
import com.user.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void getUser() {
        // Arrange
        ScmUser user = new ScmUser(1, "night 007");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        ScmUser result01 = userService.getUser(1);
        Exception thrown = assertThrows(UserNotFoundException.class, () -> userService.getUser(2));

        // Assert
        assertEquals("night 007", result01.getFullName());
        assertTrue(thrown.getMessage().contains("User id:"));
    }

    @Test
    void getAddress() {
        // Arrange
        Address address = new Address(11, 22);
        address.setAddress("test address");
        address.setDefault(1);
        when(addressRepository.findAddress(22)).thenReturn(Optional.of(address));
        when(addressRepository.findAddress(33)).thenReturn(Optional.empty());

        // Act
        UserService userService = new UserService();
        userService.setAddressRepository(addressRepository);
        Address result01 = userService.getAddress(22);
        Address result02 = userService.getAddress(33);

        // Assert
        assertEquals("test address", result01.getAddress());
        assertNull(result02);
    }
}