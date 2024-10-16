package com.hpbt.userservice.repositories;

import com.hpbt.userservice.entities.AccessKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, Integer> {
    Optional<AccessKey> findById(int accessKeyId);
    Optional<AccessKey> findByApiKey(String apiKey);
    Optional<AccessKey> findByUserId(int userId);
    Page<AccessKey> findAllByUserId(int userId, Pageable pageable);
}
