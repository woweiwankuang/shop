package com.example.soldrec.domain.repository;

import com.example.soldrec.domain.model.SoldRec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 通过时间查询
     * @param userId 当前用户id
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    List<SoldRec> findAllByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime, Sort sort);

    /**
     * 通过顾客id查询
     * @param customerIds 顾客id
     * @param userId 当前用户id
     */
    List<SoldRec> findAllByUserIdAndCustomerIdIn(int userId, List<Integer> customerIds, Sort sort);

    /**
     * 统计时间段内的销售总额
     */
    @Query(value = "select  COALESCE(sum(price),0)  from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumPriceByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);

    /**
     * 统计时间段内的成本总额
     */
    @Query(value = "select  COALESCE(sum(cost),0) from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumCostByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);

    /**
     * 统计时间段内的邮费总额
     */
    @Query(value = "select  COALESCE(sum(postage),0) from sold_rec where userId = ?1 and soldTime between ?2 and ?3", nativeQuery = true)
    double sumPostageByUserIdAndSoldTimeBetween(int userId, Long startTime, Long endTime);

    /**
     * 通过卖家用户ID和买家用户ID查询有单号的记录
     * @param userId 卖家id
     * @param customerIds 买家id
     */
    Page<SoldRec> findAllByUserIdAndTrackingNumberNotNullAndCustomerIdIn(Integer userId, Iterable<Integer> customerIds, Pageable pageable);
}
