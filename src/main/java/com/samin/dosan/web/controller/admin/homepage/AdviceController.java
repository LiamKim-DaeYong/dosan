package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.Advice;
import com.samin.dosan.domain.homepage.advice.AdviceService;
import com.samin.dosan.domain.homepage.advice.AdviceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/advice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdviceController {

    private final AdviceService adviceService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Advice> result = adviceService.findAll(searchParam, pageable);
        model.addAttribute("result", result);
        model.addAttribute("types", AdviceType.values());

        return "homepage/advice/advice";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 15; i++) {
            Advice advice = Advice.builder()
                    .agree("Y")
                    .status(null)
                    .insttNm("삼인"+i)
                    .depart("부서"+i)
                    .applicant("테스터"+i)
                    .phone("010-1111-1111")
                    .adviceType(AdviceType.ADMISSION)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            adviceService.save(advice);

            Advice advice2 = Advice.builder()
                    .agree("Y")
                    .status(null)
                    .insttNm("삼인"+i)
                    .depart("부서"+i)
                    .applicant("테스터"+i)
                    .phone("010-1111-1111")
                    .adviceType(AdviceType.FAMILY)
                    .regDt(LocalDate.now())
                    .used(Used.Y)
                    .build();

            adviceService.save(advice2);
        }
    }
}
