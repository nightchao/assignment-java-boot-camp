package com.user.repo;

import com.user.entity.ScmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ScmUser, Integer> {

    Optional<ScmUser> findById(int userId);
}
