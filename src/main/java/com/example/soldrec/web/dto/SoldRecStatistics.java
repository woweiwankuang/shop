package com.example.soldrec.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 销售相关统计信息
 */
@Data
@NoArgsConstructor
public class SoldRecStatistics {
    /**
     * 销售总额
     */
    private double totalPrice;

    /**
     * 总成本
     */
    private double totalCost;

    /**
     * 总邮费
     */
    private double totalPostage;

    /**
     * 总利润
     */
    private double totalProfit;

    public SoldRecStatistics(double totalPrice, double totalCost, double totalPostage) {
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.totalPostage = totalPostage;
        this.totalProfit = this.totalPrice - this.totalCost - this.totalPostage;
    }
}
