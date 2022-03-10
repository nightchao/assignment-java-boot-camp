package com.user.service;

import com.exception.AddressNotFoundException;
import com.exception.UserNotFoundException;
import com.user.entity.Address;
import com.user.entity.ScmUser;
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
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException(userId);
    }

    public Address getAddress(int userId) {
        Optional<Address> address = addressRepository.findAddress(userId);
        if (address.isPresent()) {
            return address.get();
        }

        throw new AddressNotFoundException(userId);
    }
}
