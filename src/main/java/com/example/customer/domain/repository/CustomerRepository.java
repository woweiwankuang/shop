package com.example.customer.domain.repository;

import com.example.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 顾客仓储
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * 通过姓名模糊查询顾客
     * @param realName 姓名
     */
    List<Customer> findAllByRealNameContaining(String realName);
}
