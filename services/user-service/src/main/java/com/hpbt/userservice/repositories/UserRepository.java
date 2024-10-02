package com.hpbt.userservice.repositories;

import com.hpbt.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(int id);
    User findUserById(int id);

    Optional<User>findByUsername(String username);
    Set<User> findAllByOrderByIdDesc();
}
