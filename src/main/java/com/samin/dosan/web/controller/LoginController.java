package com.samin.dosan.web.controller;

import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        initAdminUser();
        return "login";
    }

    @GetMapping("/admin/init")
    public String initAdmin() {
        initAdminUser();
        return "/login";
    }

    private void initAdminUser() {
        User user = userService.save(User.builder()
                .userId("admin")
                .password(passwordEncoder.encode("1111"))
                .userNm("관리자")
                .role("ROLE_ADMIN")
                .build());

        userService.save(user);
    }
}
