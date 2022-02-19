package com.example.shopping.product.service;

import com.example.shopping.product.db.ScmUser;
import com.example.shopping.product.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ScmUser getUser(int userId) {
        Optional<ScmUser> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
