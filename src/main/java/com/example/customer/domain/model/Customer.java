package com.example.customer.domain.model;

import com.example.common.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "姓名不能为空")
    private String realName;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "手机号格式不正确")
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
    @NotEmpty
    private List<String> addresss;

    /**
     * 所属用户id
     */
    private int userId;
}
