package com.example.stock.domain.model;

import com.example.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 库存
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "stock")
public class Stock extends AbstractEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 数量
     */
    private int num;

    /**
     * 总价
     */
    private double price;

    /**
     * 规格
     */
    private String specification;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 所属用户id
     */
    private int userId;
}
