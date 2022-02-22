package com.samin.dosan.web.training.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/data")
public class TrainingDataController {

    @GetMapping("/consultation")
    public String consultation() {
        return "user/data/consultation/list";
    }

    @GetMapping("/operation")
    public String operation() {
        return "user/data/operation/list";
    }

    @GetMapping("/branch")
    public String branch() {
        return "user/data/branch/list";
    }

    @GetMapping("/lecture")
    public String lecture() {
        return "user/data/lecture/list";
    }
}
