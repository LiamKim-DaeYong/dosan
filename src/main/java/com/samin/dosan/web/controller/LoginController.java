package com.samin.dosan.web.controller;

import com.samin.dosan.domain.user.Role;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostConstruct
    public void init() {
        userService.save(User.of("admin", "1111", "관리자", Role.ROLE_ADMIN));
        userService.save(User.of("employee", "1111", "임직원", Role.ROLE_EMPLOYEE));
        userService.save(User.of("educator", "1111", "지도위원", Role.ROLE_EDUCATOR));
    }
}
