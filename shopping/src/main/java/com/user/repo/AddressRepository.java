package com.user.repo;

import com.shopping.product.db.Basket;
import com.user.db.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT * FROM ADDRESS A WHERE A.USER_ID = ?1 and A.IS_DEFAULT = 1", nativeQuery = true)
    Optional<Address> findAddress(int userId);
}
