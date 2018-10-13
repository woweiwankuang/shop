package com.example.express.web.request;

import com.example.common.exception.AmazingException;
import com.example.express.domain.request.ExpressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpressRequestImpl implements ExpressRequest {

    private final KdniaoTrackQueryAPI kdniaoTrackQueryAPI;

    @Override
    public String getExpress(String expCode, String expNo) {
        try {
            return kdniaoTrackQueryAPI.getOrderTracesByJson(expCode, expNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AmazingException("查询快递物流失败");
        }
    }
}
