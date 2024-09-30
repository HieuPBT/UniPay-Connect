package com.hpbt.paymentgatewayservice.repositories;

import com.hpbt.paymentgatewayservice.entities.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGatewayRepository extends JpaRepository<PaymentLog, Integer> {

}
