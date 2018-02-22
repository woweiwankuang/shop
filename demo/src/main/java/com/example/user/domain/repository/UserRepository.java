package com.example.user.domain.repository;

import com.example.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓储
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
