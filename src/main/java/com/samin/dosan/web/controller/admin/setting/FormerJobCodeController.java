package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_code.FormerCode;
import com.samin.dosan.domain.setting.former_code.FormerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/setting/former-job-code")
public class FormerJobCodeController {

    private final FormerService formerService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                            Model model) {
        Page<FormerCode> result = formerService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/setting/former_job_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute FormerCode formerCode) {
        return "admin/setting/former/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        FormerCode formerCode = formerService.findById(id);
        model.addAttribute("former", formerCode);

        return "admin/setting/former_job_code/editView::#form";
    }

    @PostConstruct
    public void init() {
        FormerCode formerCode = FormerCode.builder()
                .formerNm("초등교원")
                .used(Used.Y)
                .build();

        formerService.save(formerCode);
    }
}
