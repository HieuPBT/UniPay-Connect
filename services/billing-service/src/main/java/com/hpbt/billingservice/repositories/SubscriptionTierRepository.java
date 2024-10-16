package com.hpbt.billingservice.repositories;

import com.hpbt.billingservice.entities.SubscriptionTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTierRepository extends JpaRepository<SubscriptionTier, Long> {
    SubscriptionTier findByName(String name);
    Optional<SubscriptionTier> findById(long id);
}
