package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sikongyan on 2018/1/26.
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public String getTestStr(){
        return "success";
    }
}
