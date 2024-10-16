package com.hpbt.userservice.repositories;

import com.hpbt.userservice.entities.AccessKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, Integer> {
}
