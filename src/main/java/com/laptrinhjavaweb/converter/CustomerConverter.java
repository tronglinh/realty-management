package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.CustomerSearchResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    ModelMapper modelMapper;

    public CustomerEntity covertToEntity(CustomerDTO customerDTO){
        CustomerEntity result = modelMapper.map(customerDTO, CustomerEntity.class);
        return result;
    }
    public CustomerDTO convertToDTO(CustomerEntity customerEntity){
        CustomerDTO result = modelMapper.map(customerEntity, CustomerDTO.class);
        return result;
    }
    public CustomerSearchResponse covertToCustomerSearchResponse(CustomerEntity customerEntity){
        CustomerSearchResponse result = modelMapper.map(customerEntity, CustomerSearchResponse.class);

        return result;
    }


}
