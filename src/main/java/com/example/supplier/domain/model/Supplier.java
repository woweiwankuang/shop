package com.example.supplier.domain.model;

import com.example.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 供应商
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "supplier")
public class Supplier extends AbstractEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属用户id
     */
    private int userId;
}
