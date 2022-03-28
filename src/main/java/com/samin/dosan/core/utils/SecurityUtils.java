package com.samin.dosan.core.utils;

import com.samin.dosan.domain.user.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static SessionUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (SessionUser) authentication.getPrincipal();
    }
}
