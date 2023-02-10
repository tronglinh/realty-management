package com.laptrinhjavaweb.dto;

import java.util.Date;

public class TransactionDetailDTO {
    private String note;
    private Date createdDate;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
