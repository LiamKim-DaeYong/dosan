package com.samin.dosan.web.controller.admin.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/homepage/training-inquiry")
public class TrainingInquiryController {

    @GetMapping
    public String mainView() {
        return null;
    }

    @GetMapping("/add")
    public String addView() {
        return null;
    }
}
