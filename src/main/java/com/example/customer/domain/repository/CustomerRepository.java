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
     * @param userId 所属用户id
     * @param realName 姓名
     */
    List<Customer> findAllByUserIdAndRealNameContaining(int userId, String realName);

    /**
     * 通过手机号查询
     * @param phoneNum 手机号
     */
    List<Customer> findAllByPhoneNum(String phoneNum);
}
