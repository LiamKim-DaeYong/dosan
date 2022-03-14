package com.samin.dosan.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String method = request.getMethod();

        if (!method.equals(HttpMethod.GET)) {
            String requestURI = request.getRequestURI();
            requestURI = requestURI.substring(1).replaceAll("/", ".");

            log.info("requestURI={}", requestURI);
            log.info("method={}", method);
        }
    }
}
