package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> getCustomer(CustomerSearchRequest customerSearchRequest);
    List<CustomerEntity> findCustomerByUser(Long staffId);
}
