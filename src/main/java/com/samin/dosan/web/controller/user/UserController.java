package com.samin.dosan.web.controller.user;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import com.samin.dosan.domain.setting.course.CourseType;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/manager/{type}")
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        return "user/users";
    }
}
