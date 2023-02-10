package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
    Map<String, String> getTransactions();
    List<TransactionDTO> getTransactions(Long id);
    void save(TransactionDTO transactionDTO);
}
