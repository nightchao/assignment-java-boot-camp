package com.example.shopping.product.repo;

import com.example.shopping.product.db.ScmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findById() {
        // Arrange
        ScmUser user = new ScmUser(1, "test fullName");
        userRepository.save(user);

        // Act
        Optional<ScmUser> result01 = userRepository.findById(1);

        // Assert
        assertTrue(result01.isPresent());
        assertEquals("test fullName", result01.get().getFullName());
    }
}