package com.samin.dosan.web.controller.employee.completion;

import com.samin.dosan.core.parameter.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/completion/training-num-gen")
public class TrainingNumGenController {

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam) {
        return "employee/completion/training_num_gen/mainView";
    }
}
