package com.example.soldrec.domain.repository;

import com.example.soldrec.domain.model.SoldRec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 创建原因：销售记录仓储
 */
public interface SoldRecRepository extends JpaRepository<SoldRec, Integer> {

    /**
     * 通过顾客id查询
     * @param customerIds 顾客id
     */
    List<SoldRec> findAllByCustomerIdIn(List<Integer> customerIds, Sort sort);

    /**
     * 统计时间段内的销售总额
     */
    @Query(value = "select  sum(price) from sold_rec where soldTime between ?1 and ?2", nativeQuery = true)
    double sumPriceBySoldTimeBetween(Long startTime, Long endTime);

    /**
     * 统计时间段内的成本总额
     */
    @Query(value = "select  sum(cost) from sold_rec where soldTime between ?1 and ?2", nativeQuery = true)
    double sumCostBySoldTimeBetween(Long startTime, Long endTime);

    /**
     * 统计时间段内的邮费总额
     */
    @Query(value = "select  sum(postage) from sold_rec where soldTime between ?1 and ?2", nativeQuery = true)
    double sumPostageBySoldTimeBetween(Long startTime, Long endTime);
}
