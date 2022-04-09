package com.samin.dosan.core.utils.file;

import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static SessionUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SessionUser) authentication.getPrincipal();
    }

    public static String getLoginUserId() {
        return getLoginUser().getUserId();
    }

    public static String getRole() {
        return getLoginUser().getRole()
                .replaceAll("ROLE_", "").toLowerCase();
    }

    public static boolean isAdmin() {
        return getLoginUser().getRole().equals(Role.ROLE_ADMIN.name());
    }

    public static boolean isCreator(String userId) {
        return getLoginUser().getUserId().equals(userId);
    }

    public static boolean isAuthorization(String userId) {
        return isAdmin() || isCreator(userId);
    }
}
