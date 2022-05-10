package com.example.oss.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Base64File 文件请求
 */
@Getter
@Setter
public class Base64File {
    /**
     * 文件名
     */
    private String filename;
    /**
     * base64转码后的内容
     */
    private String body;
    /**
     * 文件类型
     */
    private String contentType;
}
