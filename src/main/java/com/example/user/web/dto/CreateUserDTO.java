package com.example.user.web.dto;

import com.example.common.domain.Authority;
import com.example.user.domain.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Collections;

/**
 * 创建原因: 创建用户DTO
 */
@Data
public class CreateUserDTO {
    //////////////账号信息///////////////
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 6, max = 18, message = "密码长度6-18")
    private String password;

    /**
     * 获取账号
     */
    public User extractUser(){
        return new User(this.username, this.password, Collections.singleton(new Authority(Authority.USER_AUTHORITY)));
    }
}
