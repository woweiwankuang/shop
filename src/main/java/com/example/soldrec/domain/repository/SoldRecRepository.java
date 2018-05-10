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
     * 查询权限下的所有顾客
     * @param userId 当前用户id
     */
    List<SoldRec> findAllByUserId(int userId, Sort sort);

    /**
     * 通过顾客id查询
     * @param customerIds 顾客id
     * @param userId 当前用户id
     */
    List<SoldRec> findAllByUserIdAndCustomerIdIn(int userId, List<Integer> customerIds, Sort sort);

    /**
     * 统计时间段内的销售总额
     */
    @Query(value = "select  sum(price) from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumPriceByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);

    /**
     * 统计时间段内的成本总额
     */
    @Query(value = "select  sum(cost) from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumCostByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);

    /**
     * 统计时间段内的邮费总额
     */
    @Query(value = "select  sum(postage) from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumPostageByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);
}
