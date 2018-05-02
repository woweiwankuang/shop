package com.example.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Access(AccessType.FIELD)
public class Authority implements GrantedAuthority {
    /**
     * 服务的权限
     */
    public static final String CLIENT_AUTHORITY="ROLE_CLIENT";
    /**
     * 用户的权限
     */
    public static final String USER_AUTHORITY="ROLE_USER";
    /**
     * 管理员
     */
    public static final String PLATFORM_AUTHORITY="ROLE_ADMIN";
    /**
     * 权限
     */
    @NotNull
    private String authority;
}
