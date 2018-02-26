package com.example.customer.web;

import com.example.customer.domain.model.Customer;
import com.example.customer.domain.repository.CustomerRepository;
import com.example.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 顾客控制层
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    /**
     * 添加顾客
     * @param customer 顾客
     */
    @PostMapping("/customers")
    public Integer addCustomer(@RequestBody @Valid Customer customer) {
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

    /**
     * 查询单个客户
     * @param id 顾客id
     */
    @GetMapping("/customers/{id}")
    public Customer queryCustomer(@PathVariable Integer id){
        return customerRepository.findOne(id);
    }

    /**
     * 更新顾客信息
     * @param id 顾客id
     * @param customer 顾客信息
     */
    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomers(@PathVariable Integer id, @RequestBody Customer customer) {
        customerRepository.save(customer);
    }
}
