package com.colgsam.springboot.config;

import com.colgsam.springboot.config.interceptor.JwtInterceptor;
import com.colgsam.springboot.config.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")    // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/user/login", "/user/register", "/user", "/user/username/**", "/type",
                        "/file/**", "/club/**", "/activity/**");
        registry.addInterceptor(roleInterceptor())
//                .addPathPatterns("/**")    // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/user/login", "/user/register", "/user", "/user/username/**", "/type",
                        "/file/**", "/club/**", "/activity/**");


    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
    @Bean
    public RoleInterceptor roleInterceptor(){
        return new RoleInterceptor();
    }
}
