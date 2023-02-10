package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.CustomerDeleteRequest;
import com.laptrinhjavaweb.dto.request.StaffCustomerRequest;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO newCustomer){
        customerService.save(newCustomer);
        return newCustomer;
    }
    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO newCustomer){
        customerService.save(newCustomer);
        return newCustomer;
    }
    @DeleteMapping
    public void deleteCustomer(@RequestBody CustomerDeleteRequest customerDeleteRequest){
        customerService.deleteById(customerDeleteRequest);
    }
    @GetMapping("/{customerid}/staffs")
    public ResponseDTO loadStaff(@PathVariable("customerid") Long customerId){
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");

        result.setData(customerService.findStaffs(customerId));

        return result;
    }
    @PostMapping("/transaction")
    public void addTransactionNote(@RequestBody TransactionDTO transactionDTO){
        transactionService.save(transactionDTO);
    }
    @PostMapping("/assignment")
    public void assignStaff(@RequestBody StaffCustomerRequest staffCustomerRequest){
        customerService.assignStaff(staffCustomerRequest);
    }

}
