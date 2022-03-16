package com.samin.dosan.web.controller.homepage;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * NOTE : 관리자 - 수련상담신청 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/advice")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdviceController {

    private final AdviceService adviceService;

    @GetMapping
    public String mainView(@RequestParam(defaultValue = "1") int page, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
//        Page<Advice> result = adviceService.findAll(searchParam, pageable);

//        model.addAttribute("result", result);

        return "homepage/advice/advice";
    }
}
