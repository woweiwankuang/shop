package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientRegistrationService;

import javax.sql.DataSource;

/**
 * oauth2 client配置
 */
@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    @Value("${sky.oauth2.default-access-token-validity-seconds}")
    private int skyOAuth2DefaultAccessTokenValiditySeconds;
    @Value("${sky.oauth2.web.client-id}")
    private String skyOAuth2WebClientId;

    @Bean
    public ClientRegistrationService clientRegistrationService() throws Exception {
        return (ClientRegistrationService) jdbcClientDetailsServiceBuilder().build();
    }

    @Bean
    public InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder(){
        return (InMemoryClientDetailsServiceBuilder) new InMemoryClientDetailsServiceBuilder()
                .withClient(skyOAuth2WebClientId)
                    .authorizedGrantTypes("password","refresh_token")
                    .accessTokenValiditySeconds(skyOAuth2DefaultAccessTokenValiditySeconds)
                    .scopes("read").and();
    }

    @Bean
    public JdbcClientDetailsServiceBuilder jdbcClientDetailsServiceBuilder(){
        return new JdbcClientDetailsServiceBuilder().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }
}
