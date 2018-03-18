package com.example.soldrec.web.dto;

import com.example.customer.domain.model.Customer;
import com.example.soldrec.domain.model.SoldRec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 销售记录dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldRecDTO {

    /**
     * 销售记录
     */
    private SoldRec soldRec;

    /**
     * 顾客信息
     */
    private Customer customer;
}
