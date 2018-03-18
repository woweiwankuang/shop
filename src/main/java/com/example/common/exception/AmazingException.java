package com.example.common.exception;

/**
 * Created by sikongyan on 2018/3/9.
 */
public class AmazingException extends RuntimeException {

    //这里可以自定义字段

    public AmazingException(String message) {
        super(message);
    }
}
