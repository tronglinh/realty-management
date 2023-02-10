package com.laptrinhjavaweb.dto;

import java.util.List;

public class TransactionDTO {
    private Long id;
    private Long customerId;
    private String code;
    private String note;
    private List<TransactionDetailDTO> transactionDetailDTOList;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<TransactionDetailDTO> getTransactionDetailDTOList() {
        return transactionDetailDTOList;
    }

    public void setTransactionDetailDTOList(List<TransactionDetailDTO> transactionDetailDTOList) {
        this.transactionDetailDTOList = transactionDetailDTOList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
