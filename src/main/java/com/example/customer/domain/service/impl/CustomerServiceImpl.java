package com.example.customer.domain.service.impl;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.repository.CustomerRepository;
import com.example.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 顾客service
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Integer addCustomer(Customer customer){
        return customerRepository.save(customer).getId();
    }

    @Override
    public List<Customer> queryCustomersByRealName(String realName){
        return customerRepository.findAllByRealNameContaining(realName);
    }
}
