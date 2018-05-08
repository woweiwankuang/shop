package com.example.user.domain.model;

import com.example.common.domain.Authority;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 用户
 */
@EqualsAndHashCode(callSuper = false,exclude = {"password"})
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User implements UserDetails {
    /**
     * 帐号id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 用户名
     */
    @NotNull
    private String username;
    /**
     * 密码
     */
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 帐号启用
     */
    private boolean enabled=true;
    /**
     * 帐号非过期
     */
    private boolean accountNonExpired=true;
    /**
     * 帐号非锁定
     */
    private boolean accountNonLocked=true;
    /**
     * 密钥是非过期
     */
    private boolean credentialsNonExpired=true;

    /**
     * 权限
     */
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authority",joinColumns = @JoinColumn(name = "userId"))
    private Set<Authority> authorities;

    public User(String username, String password, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}
