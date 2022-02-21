package com.user.repo;

import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.model.AddressResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAddress() {
        // Arrange
        Address address = new Address(11, 22);
        address.setAddress("test address");
        address.setDefault(1);
        addressRepository.save(address);

        ScmUser user = new ScmUser(33, "test fullName");
        user.setEmail("test email");
        userRepository.save(user);

        // Act
        Optional<Address> result = addressRepository.findAddress(22);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test address", result.get().getAddress());
    }
}