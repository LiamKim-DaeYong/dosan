package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.CheckType;
import com.samin.dosan.domain.homepage.type.TrainingType;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/homepage/training-inquiry")
public class TrainingInquiryController {

    private final TrainingInquiryService trainingInquiryService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<TrainingInquiry> result = trainingInquiryService.findAll(searchParam, pageable);
        model.addAttribute("result", result);
        model.addAttribute("types", TrainingType.values());

        return "admin/homepage/training_inquiry/mainView";
    }
}
