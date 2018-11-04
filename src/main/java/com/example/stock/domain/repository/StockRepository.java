package com.example.stock.domain.repository;

import com.example.stock.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * 通过用户id和时间查询
     * @param userId 当前用户id
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    List<Stock> findAllByUserIdAndAndCreateTimeBetween(Integer userId, Long startTime, Long endTime);
}
