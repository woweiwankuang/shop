package com.example.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 创建原因：密码工具类可以全局使用
 */
@UtilityClass
public class PasswordUtil {
    private static final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    /**
     * 加密
     * @param rawPassword 初始密码
     * @return 加密后的密码
     */
    public static String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 比对
     * @param rawPassword 原始验密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
}
