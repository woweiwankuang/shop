package com.example.soldrec.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingNumberDTO {

    /**
     * 卖出时间
     */
    private Long soldTime;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 快递单号
     */
    private String trackingNumber;
}
