package com.samin.dosan.web.controller.employee.training;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiryService;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/training/training_inquiry_records")
public class InquiryRecordsController {

    private final TrainingInquiryService trainingInquiryService;

    @ModelAttribute("trainingInquiryType")
    public TrainingInquiryType[] trainingInquiryTypes() {
        return TrainingInquiryType.values();
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                           Model model) {

        Page<TrainingInquiry> result = trainingInquiryService.findAll(searchParam, pageable);
        model.addAttribute("trainingInquiryRecords", result);

        return "employee/training/training_inquiry_records/mainView";
    }
}
