package com.lucien.dap.user.authority.server.aop;

import com.lucien.dap.user.authority.client.common.LoginAuthInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class GreeMvcConfigurer extends WebMvcConfigurationSupport {

    private LoginAuthInterceptorAdapter loginAuthInterceptorAdapter;

    @Autowired
    public void setLoginAuthInterceptorAdapter(LoginAuthInterceptorAdapter loginAuthInterceptorAdapter) {
        this.loginAuthInterceptorAdapter = loginAuthInterceptorAdapter;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginAuthInterceptorAdapter).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/error");
        super.addInterceptors(registry);
    }
}
