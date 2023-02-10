package com.laptrinhjavaweb.dto.request;

public class StaffCustomerRequest {
    private Long customerId;
    private Long[] staffIds;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }
}
