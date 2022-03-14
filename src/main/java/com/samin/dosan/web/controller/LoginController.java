package com.samin.dosan.web.controller;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.domain.user.UserType;
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
        User user = userService.save(User.builder()
                .userId("admin")
                .password(passwordEncoder.encode("1111"))
                .userNm("관리자")
                .role("ROLE_ADMIN")
                .userType(UserType.ADMIN)
                .used(Used.Y)
                .build());

        userService.save(user);
    }
}
