package com.example.user.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

/**
 * 创建时间：2017/03/23:下午1:32
 * @author 梁志艳
 */
@Slf4j
@Service
public class TokenRevokeService {
    @Value("${sky.oauth2.web.client-id}")
    private String skyOAuth2WebClientId;
    private final TokenStore tokenStore;
    private final ConsumerTokenServices tokenServices;

    @Autowired
    TokenRevokeService(TokenStore tokenStore, ConsumerTokenServices tokenServices) {
        this.tokenStore = tokenStore;
        this.tokenServices = tokenServices;
    }

    /**
     * 注销token
     * @param username 用户名
     */
    public void revoke(String username){
        log.debug("revoking {}",username);
        tokenStore.findTokensByClientIdAndUserName(skyOAuth2WebClientId,username).forEach(token -> tokenServices.revokeToken(token.getValue()));
    }
}
