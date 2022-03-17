package com.samin.dosan.web.controller.admin.setting;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import com.samin.dosan.domain.setting.course.CourseType;
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
@RequestMapping("/admin/setting/course/{type}")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        CourseType courseType = CourseType.valueOf(type.toUpperCase());

        Page<Course> result = courseService.findAll(searchParam, courseType, pageable);
        model.addAttribute("result", result);
        model.addAttribute("courseTypes", CourseType.values());
        model.addAttribute("courseType", CourseType.valueOf(type.toUpperCase()));

        return "admin/setting/course/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Course saveData) {
        return "admin/setting/course/addView::#form";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);

        return "admin/setting/course/editView::#form";
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