package com.ksh.exam.springpractice1.app.base;

import com.ksh.exam.springpractice1.app.intercepter.BeforeActionIntercepter;
import com.ksh.exam.springpractice1.app.intercepter.NeedToAdminterceptor;
import com.ksh.exam.springpractice1.app.intercepter.NeedToLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final BeforeActionIntercepter beforeActionIntercepter;
    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToAdminterceptor needToAdminterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(beforeActionIntercepter);

        ir.addPathPatterns("/**");
        ir.excludePathPatterns("/favicon.ico");
        ir.excludePathPatterns("/resource/**");
        ir.excludePathPatterns("/gen/**");
        ir.excludePathPatterns("/error");

        ir = registry.addInterceptor(needToLoginInterceptor);
        ir.addPathPatterns("/article/write");
        ir.addPathPatterns("/member/me");

        ir = registry.addInterceptor(needToAdminterceptor);
        ir.addPathPatterns("/adm/**");
    }
}
