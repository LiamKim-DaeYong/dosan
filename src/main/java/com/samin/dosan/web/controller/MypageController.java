package com.samin.dosan.web.controller;

import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final UserService userService;

    @GetMapping("/admin")
    public String admin() {
        return "mypage/admin";
    }

    @GetMapping("/{userId}")
    public String mypage(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        return "mypage/other";
    }
}
