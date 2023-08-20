package com.ksh.exam.springpractice1.app.intercepter;

import com.ksh.exam.springpractice1.app.base.Rq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class BeforeActionIntercepter implements HandlerInterceptor {
    private final Rq rq;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("BeforeActionIntercepter::preHandle 실행됨");

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
