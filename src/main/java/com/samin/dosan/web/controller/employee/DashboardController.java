package com.samin.dosan.web.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("employeeDashboard")
@RequestMapping("/employee/dashboard")
public class DashboardController {

    @GetMapping
    public String mainView() {
        return "employee/dashboard";
    }
}
