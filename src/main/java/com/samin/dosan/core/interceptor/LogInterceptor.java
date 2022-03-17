package com.samin.dosan.core.interceptor;

import com.samin.dosan.domain.usagelog.UsageLogService;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final UsageLogService usageLogService;
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String method = request.getMethod();

        if (!method.equals(HttpMethod.GET.toString())) {
//            History history = History.builder()
//                    .user(getLoginUser())
//                    .logAt(LocalDateTime.now())
//                    .systemNm(getSystemNm(request))
//                    .method(MethodType.valueOf(method).getDescription())
//                    .build();

//            historyService.save(history);
        }
    }

    private String getSystemNm(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        requestURI = "menu." + requestURI.substring(1).split("/")[0];
        return messageSource.getMessage(requestURI, null, request.getLocale());
    }

    private User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findById(authentication.getName()).get();
    }
}
