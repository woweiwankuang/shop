package com.example.user.domain.service;

import com.example.user.domain.model.User;

/**
 * 用户service
 */
public interface UserService {

    /**
     * 添加用户
     * @param user 用户
     */
    Integer addUser(User user);
}
