package com.samin.dosan.web.controller.educator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("educatorDashboard")
@RequestMapping("/educator/dashboard")
public class DashboardController {

    @GetMapping
    public String mainView() {
        return "educator/dashboard";
    }
}
