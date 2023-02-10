package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    void deleteByCustomerId(Long id);
    List<TransactionEntity> findByCustomerId(Long id);
    List<TransactionEntity> findByCustomerIdAndCode(Long id, String code);
}
