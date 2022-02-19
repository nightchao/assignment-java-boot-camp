package com.example.shopping.product.repo;

import com.example.shopping.product.db.ScmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ScmUser, Integer> {

    Optional<ScmUser> findById(int userId);
}
