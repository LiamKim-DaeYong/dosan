package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
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

    private final FormerJobCodeService formerJobCodeService;

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                            Model model) {
        Page<FormerJobCode> result = formerJobCodeService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/setting/former_job_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute FormerJobCode formerCode) {
        return "admin/setting/former_job_code/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        FormerJobCode formerJobCode = formerJobCodeService.findById(id);
        model.addAttribute("formerJobCode", formerJobCode);

        return "admin/setting/former_job_code/editView::#form";
    }

    @PostConstruct
    public void init() {
        FormerJobCode formerCode = FormerJobCode.builder()
                .formerNm("초등교원")
                .used(Used.Y)
                .build();

        formerJobCodeService.save(formerCode);
    }
}
