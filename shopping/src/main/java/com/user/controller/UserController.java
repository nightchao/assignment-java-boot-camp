package com.user.controller;

import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.model.AddressResponse;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/address")
    public AddressResponse searchProduct(@PathVariable Integer userId) {
        ScmUser user = userService.getUser(userId);
        Address address = userService.getAddress(user.getUserId());

        AddressResponse response = new AddressResponse();
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setAddressId(address.getAddressId());
        response.setAddress(address.getAddress());
        response.setDistrict(address.getDistrict());
        response.setProvince(address.getProvince());
        response.setTelephone(address.getTelephone());
        return response;
    }
}
