package com.example.soldrec.domain.repository;

import com.example.soldrec.domain.model.SoldRec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 创建原因：销售记录仓储
 */
public interface SoldRecRepository extends JpaRepository<SoldRec, Long> {

    /**
     * 通过顾客id查询
     * @param customerIds 顾客id
     */
    List<SoldRec> findAllByCustomerIdIn(List<Integer> customerIds, Sort sort);
}
