package com.example.supplier.domain.repository;

import com.example.supplier.domain.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    /**
     * 通过姓名模糊查询顾客
     * @param userId 所属用户id
     * @param name 姓名
     */
    List<Supplier> findAllByUserIdAndNameContaining(int userId, String name);
}
