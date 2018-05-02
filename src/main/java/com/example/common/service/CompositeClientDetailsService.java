package com.example.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

/**
 * 提供内存配置优先，数据库补充的client管理
 */
@RequiredArgsConstructor
public class CompositeClientDetailsService implements ClientDetailsService{
    private final InMemoryClientDetailsService inMemoryClientDetailsService;
    private final JdbcClientDetailsService jdbcClientDetailsService;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try{
            return inMemoryClientDetailsService.loadClientByClientId(clientId);
        }catch (NoSuchClientException e){
            return jdbcClientDetailsService.loadClientByClientId(clientId);
        }
    }
}
