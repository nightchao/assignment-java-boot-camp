package com.user.repo;

import com.user.db.Address;
import com.user.db.AddressId;
import com.user.db.ScmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAddress() {
        // Arrange
        AddressId id = new AddressId(11, 22);
        Address address = new Address(id.getAddressId(), id.getUserId());
        address.setAddress("test address");
        address.setDistrict("test district");
        address.setProvince("test province");
        address.setTelephone("test telephone");
        address.setDefault(1);
        addressRepository.save(address);

        ScmUser user = new ScmUser(33, "test fullName");
        user.setEmail("test email");
        userRepository.save(user);

        // Act
        Optional<Address> result = addressRepository.findAddress(22);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(11, result.get().getAddressId());
        assertEquals(22, result.get().getUserId());
        assertEquals("test address", result.get().getAddress());
        assertEquals("test district", result.get().getDistrict());
        assertEquals("test province", result.get().getProvince());
        assertEquals("test telephone", result.get().getTelephone());
    }
}