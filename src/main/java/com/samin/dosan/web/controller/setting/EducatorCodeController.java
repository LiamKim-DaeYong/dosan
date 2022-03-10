package com.samin.dosan.web.controller.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeService;
import com.samin.dosan.domain.setting.educator.EducatorCodeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/setting/educator/{type}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EducatorCodeController {

    private final EducatorCodeService educatorCodeService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        EducatorCodeType educatorCodeType = EducatorCodeType.valueOf(type.toUpperCase());

        Page<EducatorCode> result = educatorCodeService.findAll(searchParam, educatorCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("educatorCodeType", educatorCodeType);
        model.addAttribute("educatorCodeTypes", EducatorCodeType.values());

        return "setting/educator/educators";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute EducatorCode saveData) {
        return "setting/educator/addForm::#form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        EducatorCode educatorCode = educatorCodeService.findById(id);
        model.addAttribute("educatorCode", educatorCode);

        return "setting/educator/editForm::#form";
    }

    @PostConstruct
    public void init() {
        EducatorCode educatorCode1 = EducatorCode.builder()
                .id(1L)
                .code("지도위원")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        educatorCodeService.save(educatorCode1);

        EducatorCode educatorCode2 = EducatorCode.builder()
                .id(2L)
                .code("예절지도위원")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        educatorCodeService.save(educatorCode2);

        EducatorCode educatorCode3 = EducatorCode.builder()
                .id(3L)
                .code("휴직지도위원")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        educatorCodeService.save(educatorCode3);

        EducatorCode educatorCode4 = EducatorCode.builder()
                .id(4L)
                .code("퇴직지도위원")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        educatorCodeService.save(educatorCode4);

        for (int i = 1; i < 100; i++) {
            EducatorCode educatorCode = EducatorCode.builder()
                    .id(Long.valueOf(i))
                    .code("담당 " + i)
                    .educatorCodeType(EducatorCodeType.CHARGE)
                    .used(Used.Y)
                    .build();

            educatorCodeService.save(educatorCode);
        }

        for (int i = 1; i < 100; i++) {
            EducatorCode educatorCode = EducatorCode.builder()
                    .id(Long.valueOf(i))
                    .code("소속 " + i)
                    .educatorCodeType(EducatorCodeType.BELONG)
                    .used(Used.Y)
                    .build();

            educatorCodeService.save(educatorCode);
        }

        for (int i = 1; i < 100; i++) {
            EducatorCode educatorCode = EducatorCode.builder()
                    .id(Long.valueOf(i))
                    .code("지부 " + i)
                    .educatorCodeType(EducatorCodeType.BRANCH)
                    .used(Used.Y)
                    .build();

            educatorCodeService.save(educatorCode);
        }
    }
}
