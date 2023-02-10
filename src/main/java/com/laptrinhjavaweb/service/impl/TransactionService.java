package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.TransactionDetailDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TransactionService implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionConverter transactionConverter;

    @Override
    public Map<String, String> getTransactions() {
        Map<String, String> transactions = new HashMap<>();
        for (TransactionTypeEnum item : TransactionTypeEnum.values()) {
            transactions.put(item.toString(), item.getTransactionTypeValue());
        }
        return transactions;
    }

    @Override
    public List<TransactionDTO> getTransactions(Long id) {
        Map<String,String> transactionsMap = getTransactions();
        List<TransactionDTO> transactionDTO = new ArrayList<>();
        for(Map.Entry<String, String> entry: transactionsMap.entrySet()){
            List<TransactionDetailDTO> transactionDetailDTOList = new ArrayList<>();
            TransactionDTO transactionDTO1 = new TransactionDTO();
            transactionDTO1.setCode(entry.getValue());
            List<TransactionEntity> transactionEntityList = transactionRepository.findByCustomerIdAndCode(id,entry.getKey());
            for(TransactionEntity item: transactionEntityList){
                transactionDetailDTOList.add(transactionConverter.convertToTransactionDetailDTO(item));
            }
            transactionDTO1.setTransactionDetailDTOList(transactionDetailDTOList);
            transactionDTO.add(transactionDTO1);
        }
        return transactionDTO;
    }
    @Transactional
    @Override
    public void save(TransactionDTO transactionDTO) {
        Map<String,String> transactionsMap = getTransactions();
        for(Map.Entry<String, String>  entry: transactionsMap.entrySet()){
            if(entry.getValue().equals(transactionDTO.getCode())){
                transactionDTO.setCode(entry.getKey());
                break;
            }
        }
        transactionRepository.save(transactionConverter.convertToEntity(transactionDTO));
    }


}
