package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.setting.educator_code.EducatorCode;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeService;
import com.samin.dosan.domain.setting.educator_code.EducatorCodeType;
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
@RequestMapping("/admin/setting/educator-code/{type}")
public class EducatorCodeController {

    private final EducatorCodeService educatorCodeService;

    @ModelAttribute("educatorCodeTypes")
    public EducatorCodeType[] educatorCodeTypes() {
        return EducatorCodeType.values();
    }

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        EducatorCodeType educatorCodeType = EducatorCodeType.valueOf(StrUtils.urlToEnumName(type));

        Page<EducatorCode> result = educatorCodeService.findAll(searchParam, educatorCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("educatorCodeType", educatorCodeType);

        return "admin/setting/educator_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute EducatorCode educatorCode) {
        return "admin/setting/educator_code/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        EducatorCode educatorCode = educatorCodeService.findById(id);
        model.addAttribute("educatorCode", educatorCode);

        return "admin/setting/educator_code/editView::#form";
    }

    @PostConstruct
    public void init() {
        educatorCodeService.save(EducatorCode.of("지도위원", EducatorCodeType.TYPE));
        educatorCodeService.save(EducatorCode.of("예절지도위원", EducatorCodeType.TYPE));

        educatorCodeService.save(EducatorCode.of("담당 테스트", EducatorCodeType.ASSIGNED_TASK));
        educatorCodeService.save(EducatorCode.of("소속 테스트", EducatorCodeType.TEAM));
        educatorCodeService.save(EducatorCode.of("지부 테스트", EducatorCodeType.BRANCH));
    }
}
