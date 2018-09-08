package com.example.userbindinfo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户绑定信息
 */
@Data
@Entity
@Table(name = "user_bind_info")
@NoArgsConstructor
public class UserBindInfo {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 绑定码
     */
    private String code;

    public UserBindInfo(Integer userId, String code) {
        this.userId = userId;
        this.code = code;
    }
}
