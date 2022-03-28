package com.samin.dosan.web.controller.employee.training.school;

import com.samin.dosan.core.parameter.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/training/school/program")
public class SchoolProgramController {

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam) {
        return "employee/training/school/program/mainView";
    }

    @GetMapping("/add")
    public String addView() {
        return "employee/training/school/program/addView";
    }
}
