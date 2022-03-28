package com.samin.dosan.web.controller.employee.training.archive;

import com.samin.dosan.core.parameter.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("employeeOperationalController")
@RequiredArgsConstructor
@RequestMapping("/employee/archive/operational")
public class OperationalController {

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam) {
        return "employee/archive/operational/mainView";
    }
}
