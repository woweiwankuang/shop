package com.example.customer.domain.service;

import com.example.customer.domain.model.Customer;

import java.util.List;

/**
 * 顾客service
 */
public interface CustomerService {

    /**
     * 添加顾客
     * @param customer 顾客
     */
    Integer addCustomer(Customer customer);

    /**
     * 通过用户姓名模糊查询
     * @param realName 姓名
     */
    List<Customer> queryCustomersByRealName(String realName, int userId);
}
