package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.TransactionDetailDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    ModelMapper modelMapper;

    public TransactionDTO convertToDTO(TransactionEntity transactionEntity){
        TransactionDTO result = modelMapper.map(transactionEntity, TransactionDTO.class);
        return result;
    }
    public TransactionDetailDTO convertToTransactionDetailDTO(TransactionEntity transactionEntity){
        TransactionDetailDTO result = modelMapper.map(transactionEntity, TransactionDetailDTO.class);
        return result;
    }
    public TransactionEntity convertToEntity(TransactionDTO transactionDTO){
        TransactionEntity result = modelMapper.map(transactionDTO, TransactionEntity.class);
        return result;
    }

}
