package com.user.controller;

import com.exception.AddressNotFoundException;
import com.exception.UserNotFoundException;
import com.shopping.product.model.SearchResponse;
import com.user.db.Address;
import com.user.db.ScmUser;
import com.user.model.AddressResponse;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/address")
    public AddressResponse searchProduct(@PathVariable Integer userId) {
        ScmUser user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Address address = userService.getAddress(user.getUserId());
        if (address == null) {
            throw new AddressNotFoundException(userId);
        }

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
