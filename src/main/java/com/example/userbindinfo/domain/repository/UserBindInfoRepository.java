package com.example.userbindinfo.domain.repository;

import com.example.userbindinfo.domain.model.UserBindInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创建原因：用户绑定信息仓储
 */
public interface UserBindInfoRepository extends JpaRepository<UserBindInfo, Integer> {

    /**
     * 通过绑定码查询
     * @param code 绑定码
     */
    UserBindInfo findOneByCode(String code);

    /**
     * 通过用户id查询
     * @param userId 用户id
     */
    UserBindInfo findOneByUserId(Integer userId);

    /**
     * 判断绑定码是否存在
     * @param code 绑定码
     */
    boolean existsByCode(String code);
}
