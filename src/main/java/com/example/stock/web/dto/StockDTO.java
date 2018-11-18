package com.example.stock.web.dto;

import com.example.stock.domain.model.Stock;
import com.example.supplier.domain.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    /**
     * 库存信息
     */
    private Stock stock;

    /**
     * 供应商信息
     */
    private Supplier supplier;
}
