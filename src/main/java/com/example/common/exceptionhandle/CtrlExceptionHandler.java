package com.example.common.exceptionhandle;

import com.example.common.exception.AmazingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常拦截处理器
 */
@ControllerAdvice
public class CtrlExceptionHandler {


    //指定拦截异常类
    @ExceptionHandler(AmazingException.class)
//    @ResponseBody  指定返回类型
//    @ResponseStatus(HttpStatus.NO_CONTENT)  指定返回状态码
    public String handleAmazingException(AmazingException e){
        return e.getMessage();
    }
}
