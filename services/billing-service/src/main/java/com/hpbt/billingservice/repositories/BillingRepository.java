package com.hpbt.billingservice.repositories;

import com.hpbt.billingservice.entities.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    List<Billing> findByUserSubscriptionId(Long userSubscriptionId);

    @Query("SELECT b FROM Billing b WHERE b.userSubscription.userId = :userId AND b.createdAt BETWEEN :startDate AND :endDate")
    Page<Billing> findAllByUserId(Long userId, Instant startDate, Instant endDate, Pageable pageable);
}
