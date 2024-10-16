package com.hpbt.transactionservice.repositories;

import com.hpbt.transactionservice.entities.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypesRepository extends JpaRepository<PaymentTypes, Integer> {
}
