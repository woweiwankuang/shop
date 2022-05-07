package com.example.demo;

import com.example.utils.IpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by sikongyan on 2018/1/26.
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public Date getTestStr(HttpServletRequest request) {
        //获取IP地址
        String ipAddress = IpUtils.getIpAddr(request);
        return new Date();
    }
}
