package com.samin.dosan.web.controller.admin.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/homepage/training-review")
public class TrainingReviewController {

    @GetMapping
    public String mainView() {
        return "admin/homepage/training_review/mainView";
    }
}
