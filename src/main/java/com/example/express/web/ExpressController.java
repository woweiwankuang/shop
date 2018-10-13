package com.example.express.web;

import com.example.express.domain.request.ExpressRequest;
import com.example.utils.ExpressUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpressController {

    private final ExpressRequest expressRequest;

    /**
     * 查询快递物流
     * @param trackingNumber 快递号
     */
    @PostMapping("/expressSearchs")
    public String getExpress(@RequestParam String trackingNumber) {
        //解析快递号，将快递号解析成[快递公司编码,快递号]
        String[] expArray = ExpressUtil.validTrackingNumber(trackingNumber);
        String expCode = ExpressUtil.getExpCode(expArray[0]);
        return expressRequest.getExpress(expCode, expArray[1]);
    }
}
