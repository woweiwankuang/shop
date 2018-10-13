package com.example.config;

import com.example.common.service.CompositeClientDetailsService;
import com.example.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

//    private final UserDetailsService userService;

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth, List<ExtendedAuthenticationProvider> extendedAuthenticationProviders) throws Exception {
//        //添加三方拓展
//        extendedAuthenticationProviders.forEach(auth::authenticationProvider);
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return PasswordUtil.encode(charSequence);
            }

            @Override
            public boolean matches(CharSequence raw, String encode) {
                return PasswordUtil.matches(raw, encode);
            }
        };
    }

    /**
     * 认证服务的配置
     */
    @Configuration
    @EnableAuthorizationServer
    @RequiredArgsConstructor
    public static class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
        private final TokenStore tokenStore;
        private final UserDetailsService userService;
        private final AuthenticationManager authenticationManager;
        private final InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder;
        private final JdbcClientDetailsServiceBuilder jdbcClientDetailsServiceBuilder;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(new CompositeClientDetailsService((InMemoryClientDetailsService) inMemoryClientDetailsServiceBuilder.build(), (JdbcClientDetailsService) jdbcClientDetailsServiceBuilder.build()));
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.authenticationManager(authenticationManager).userDetailsService(userService).tokenStore(tokenStore).approvalStoreDisabled();
        }

//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer security) {
//            security.passwordEncoder(new ClientAuthenticationPasswordEncoder());
//        }

    }


    /**
     * 资源服务的配置
     */
    @Configuration
    @EnableResourceServer
    public static class ResourceServer extends ResourceServerConfigurerAdapter {
        private final TokenStore tokenStore;

        @Autowired
        public ResourceServer(TokenStore tokenStore) {
            this.tokenStore = tokenStore;
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/users").permitAll()
                    .antMatchers(HttpMethod.GET,"/trackingNumbers").permitAll()
                    .antMatchers(HttpMethod.POST,"/expressSearchs").permitAll()
                    .anyRequest().authenticated();
        }
    }
}
