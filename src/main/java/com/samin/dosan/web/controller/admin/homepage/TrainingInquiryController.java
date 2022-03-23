package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.code.homepage.CheckType;
import com.samin.dosan.core.code.homepage.TrainingType;
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

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1000; i++) {
            trainingInquiryService.save(TrainingInquiry.builder()
                    .agree("Y")
                    .status(CheckType.Y)
                    .insttNm("삼인"+i)
                    .depart("부서"+i)
                    .applicant("사람"+i)
                    .phone("010-1111-2222")
                    .trainingInquiryType(TrainingType.FAMILY)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build()
            );

            trainingInquiryService.save(TrainingInquiry.builder()
                    .agree("Y")
                    .status(CheckType.Y)
                    .insttNm("삼인"+i)
                    .depart("부서"+i)
                    .applicant("사람"+i)
                    .phone("010-1111-2222")
                    .trainingInquiryType(TrainingType.ORDINARY)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build()
            );
        }

    }
}
