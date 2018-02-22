package com.example.customer.domain.model;

import com.example.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

/**
 * 顾客
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {
    /**
     * 顾客名称
     */
    private String realName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 常用地址
     */
    @ElementCollection
    @CollectionTable(
            name = "customer_address_rec",
            joinColumns = @JoinColumn(name = "customerId")
    )
    @Column(name = "address")
    private List<String> addresss;
}
