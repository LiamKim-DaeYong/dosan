package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.setting.subject_code.SubjectCode;
import com.samin.dosan.domain.setting.subject_code.SubjectCodeService;
import com.samin.dosan.domain.setting.subject_code.SubjectCodeType;
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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/setting/subject-code/{type}")
public class SubjectCodeController {

    private final SubjectCodeService subjectCodeService;

    @ModelAttribute("subjectCodeTypes")
    public SubjectCodeType[] subjectCodeTypes() {
        return SubjectCodeType.values();
    }

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        SubjectCodeType subjectCodeType = SubjectCodeType.valueOf(StrUtils.urlToEnumName(type));

        Page<SubjectCode> result = subjectCodeService.findAll(searchParam, subjectCodeType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("subjectCodeType", subjectCodeType);

        return "admin/setting/subject_code/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute SubjectCode saveData) {
        return "admin/setting/subject_code/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        SubjectCode subjectCode = subjectCodeService.findById(id);
        model.addAttribute("subjectCode", subjectCode);

        return "admin/setting/subject_code/editView::#form";
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 1; i <= 1000; i++) {
//            Course course = Course.builder()
//                    .subject(i + " 과목")
//                    .content("수련 소감 및 실천다짐토의 결과 발표")
//                    .courseType(CourseType.ENTRY)
//                    .used(Used.Y)
//                    .build();
//
//            courseService.save(course);
//        }
//    }
}
