package com.example.customer.web;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 顾客控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 添加顾客
     * @param customer 顾客
     */
    @PostMapping("/customers")
    public Integer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    /**
     * 通过姓名查询客户
     * @param realName 姓名
     */
    @GetMapping("/customers")
    public List<Customer> queryCustomers(String realName) {
        return customerService.queryCustomersByRealName(realName);
    }
}
