package com.hpbt.paymentgatewayservice.repositories;

import com.hpbt.paymentgatewayservice.entities.PaymentLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentGatewayRepository extends JpaRepository<PaymentLog, Integer> {
//    Optional<Page<PaymentLog>> findAllByUserId(int customerId, Pageable pageable);
}
