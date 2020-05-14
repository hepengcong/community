package com.practice.community.config;

import com.practice.community.config.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.practice.community.config.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;
    @Autowired
    CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    @Autowired
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //配置认证方式
        auth.userDetailsService(userDetailsService());

        //配置认证方式等
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                //异常处理(权限拒绝、登录失效等)
                        and().exceptionHandling().
                authenticationEntryPoint(authenticationEntryPoint).//匿名用户访问无权限资源时的异常处理
//登入
        and().formLogin().
                permitAll().//允许所有用户
                successHandler(customizeAuthenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(customizeAuthenticationFailureHandler).//登录失败处理逻辑
//登出
        and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(customizeLogoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").//登出之后删除cookie


                and().sessionManagement().
                maximumSessions(1).
                expiredSessionStrategy(customizeSessionInformationExpiredStrategy);
        //http相关的配置，包括登入登出、异常处理、会话管理等
        super.configure(http);
    }


}
