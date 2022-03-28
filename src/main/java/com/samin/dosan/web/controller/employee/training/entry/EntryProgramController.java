package com.samin.dosan.web.controller.employee.training.entry;

import com.samin.dosan.core.parameter.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/training/entry/program")
public class EntryProgramController {

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam) {
        return "employee/training/entry/program/mainView";
    }
}
