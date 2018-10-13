package com.example.express.domain.request;

public interface ExpressRequest {

    /**
     * 通过快递公司和单号查询快递
     * @param expCode 快递公司编码
     * @param expNo 物流单号
     */
    String getExpress(String expCode, String expNo);
}
