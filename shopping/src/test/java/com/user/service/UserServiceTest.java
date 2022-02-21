package com.user.service;

import com.user.db.ScmUser;
import com.user.repo.UserRepository;
import com.user.service.UserService;
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
        ScmUser result02 = userService.getUser(2);

        // Assert
        assertEquals("night 007", result01.getFullName());
        assertNull(result02);
    }
}