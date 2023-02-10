package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.CustomerDeleteRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.dto.request.StaffCustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerSearchResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    UserConverter userConverter;

    @Autowired
    BuildingConverter buildingConverter;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public void save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.covertToEntity(customerDTO);
        if (customerDTO.getId() != null) {
            List<UserEntity> userEntityList = userRepository.findByCustomers_Id(customerDTO.getId());
            customerEntity.setUsers(userEntityList);
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return customerConverter.convertToDTO(customerEntity);
    }

    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest customerSearchRequest) {
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            customerSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        List<CustomerSearchResponse> result = new ArrayList<>();
        List<CustomerEntity> customerEntities = new ArrayList<>();
        customerEntities = customerRepository.getCustomer(customerSearchRequest);

        for (CustomerEntity item : customerEntities) {
            CustomerSearchResponse customerSearchResponse = customerConverter.covertToCustomerSearchResponse(item);
            List<UserDTO> userDTOList = new ArrayList<>();
            for (UserEntity userEntity : item.getUsers()) {
                UserDTO userDTO = userConverter.convertToDto(userEntity);
                userDTOList.add(userDTO);
            }
            customerSearchResponse.setUserDTOList(userDTOList);
            if (customerSearchResponse.getUserDTOList() != null) {
                int i = 0;
                for (UserDTO items : customerSearchResponse.getUserDTOList()) {
                    if (i == 0) {
                        customerSearchResponse.setStaffName(items.getFullName());
                    } else {
                        customerSearchResponse.setStaffName(customerSearchResponse.getStaffName() + ", " + items.getFullName());
                    }
                    i++;
                }
            }
            result.add(customerSearchResponse);
        }
        return result;
    }

    @Transactional
    @Override
    public void deleteById(CustomerDeleteRequest customerDeleteRequest) {
        for (Long data : customerDeleteRequest.getCustomerIds()) {
            transactionRepository.deleteByCustomerId(data);
            customerRepository.delete(data);
        }

    }

    @Override
    public List<StaffResponseDTO> findStaffs(Long customerId) {
        List<StaffResponseDTO> result = new ArrayList<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        for (UserEntity item : staffs) {
            StaffResponseDTO staffResponseDTO = userConverter.convertToStaffDTO(item);
            result.add(staffResponseDTO);
        }
        for (StaffResponseDTO staffResponseDTO : result) {
            List<CustomerEntity> customerEntities = customerRepository.findCustomerByUser(staffResponseDTO.getId());
            for (CustomerEntity item : customerEntities) {
                CustomerSearchResponse customerSearchResponse = customerConverter.covertToCustomerSearchResponse(item);
                if (customerSearchResponse.getId() == customerId) {
                    staffResponseDTO.setChecked("checked");
                }
            }
        }
        return result;
    }

    @Transactional
    @Override
    public void assignStaff(StaffCustomerRequest staffCustomerRequest) {
        Long[] checkedRequestStaffsId = staffCustomerRequest.getStaffIds();
        List<UserEntity> userList = new ArrayList<>();
        CustomerEntity customerEntity = customerRepository.findOne(staffCustomerRequest.getCustomerId());
        for (Long item : checkedRequestStaffsId) {

            userList.add(userRepository.findOne(item));
        }
        customerEntity.setUsers(userList);
        customerRepository.save(customerEntity);
    }
}
