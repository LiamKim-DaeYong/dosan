package com.samin.dosan.web.controller;

import com.samin.dosan.core.code.Gender;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.employee.EmployeeCodeService;
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
    private final EmployeeCodeService employeeCodeService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostConstruct
    public void init() {
        userService.save(User.builder()
                .userId("admin")
                .password("1111")
                .userNm("관리자")
                .gender(Gender.MAN)
                .role("ROLE_ADMIN")
                .userType(UserType.ADMIN)
                .used(Used.Y)
                .build());

        userService.save(User.builder()
                .userId("manager")
                .password("1111")
                .userNm("임직원")
                .gender(Gender.MAN)
                .role("ROLE_MANAGER")
                .userType(UserType.EMPLOYEES)
                .employeesType(employeeCodeService.findByCode("임원"))
                .used(Used.Y)
                .build());

        userService.save(User.builder()
                .userId("educator")
                .password("1111")
                .userNm("지도위원")
                .gender(Gender.MAN)
                .role("ROLE_USER")
                .userType(UserType.EDUCATOR)
                .used(Used.Y)
                .build());
    }
}
