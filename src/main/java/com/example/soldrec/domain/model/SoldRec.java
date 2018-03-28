package com.example.soldrec.domain.model;

import com.example.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 销售记录
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sold_rec")
public class SoldRec extends AbstractEntity{
    /**
     * 顾客id
     */
    private Integer customerId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 总成本
     */
    private double cost;

    /**
     * 总售价
     */
    private double price;

    /**
     * 邮费
     */
    private double postage;

    /**
     * 产品数量
     */
    private int num;

    /**
     * 卖出时间
     */
    private Long soldTime;

    /**
     * 利润
     */
    private double profit;

    /**
     * 邮寄地址
     */
    private String address;

    /**
     * 是否已经寄出
     */
    private boolean haveSend;

    /**
     * 快递单号
     */
    private String trackingNumber;

    /**
     * 所属用户id
     */
    private int userId;

}
