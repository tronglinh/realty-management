package com.laptrinhjavaweb.dto.request;

public class CustomerDeleteRequest {
    Long[] customerIds;

    public Long[] getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(Long[] customerIds) {
        this.customerIds = customerIds;
    }
}
