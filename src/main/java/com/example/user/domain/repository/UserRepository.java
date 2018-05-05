package com.example.user.domain.repository;

import com.example.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓储
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     */
    User findOneByUsername(String username);

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);

}
