package com.samin.dosan.web.controller.employee.training.archive;

import com.samin.dosan.core.parameter.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("employeeConsultationController")
@RequiredArgsConstructor
@RequestMapping("/employee/archive/consultation")
public class ConsultationController {

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam) {
        return "employee/archive/consultation/mainView";
    }
}
