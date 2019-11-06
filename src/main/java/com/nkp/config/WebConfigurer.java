package com.nkp.config;

import com.nkp.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            //registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/user/backstage","/signout","/static/**");
    }

}
