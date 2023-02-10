package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteRequest;
import com.laptrinhjavaweb.dto.request.CustomerDeleteRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.dto.request.StaffCustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerSearchResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService {
    void save(CustomerDTO customerDTO);
    CustomerDTO findById(Long id);
    List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest);
    void deleteById(CustomerDeleteRequest customerDeleteRequest);
    List<StaffResponseDTO> findStaffs(Long customerId);

    void assignStaff(StaffCustomerRequest staffCustomerRequest);
}
