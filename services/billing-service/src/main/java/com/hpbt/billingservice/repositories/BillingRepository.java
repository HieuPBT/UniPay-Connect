package com.hpbt.billingservice.repositories;

import com.hpbt.billingservice.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByUserSubscriptionId(Long userSubscriptionId);
}
