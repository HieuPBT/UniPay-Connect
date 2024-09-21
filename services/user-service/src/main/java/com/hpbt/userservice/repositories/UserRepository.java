package com.hpbt.userservice.repositories;

import com.hpbt.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(int id);
    User findUserById(int id);
}
