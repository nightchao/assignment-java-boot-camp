package com.user.service;

import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.repo.AddressRepository;
import com.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public ScmUser getUser(int userId) {
        Optional<ScmUser> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public Address getAddress(int userId) {
        Optional<Address> address = addressRepository.findAddress(userId);
        return address.orElse(null);
    }
}
