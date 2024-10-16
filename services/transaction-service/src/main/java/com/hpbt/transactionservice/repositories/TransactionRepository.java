package com.hpbt.transactionservice.repositories;

import com.hpbt.transactionservice.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findByOrderId(String orderId);

    Page<Transaction> findAllByUserId(Integer userId, Pageable pageable);
}
