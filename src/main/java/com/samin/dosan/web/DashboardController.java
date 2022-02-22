package com.samin.dosan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/admin")
    public String adminDashboard() {
        return "dashboard/admin";
    }

    @GetMapping("/manager")
    public String managerDashboard() {
        return "dashboard/manager";
    }

    @GetMapping("/user")
    public String userDashboard() {
        return "dashboard/user";
    }
}
