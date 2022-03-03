package com.samin.dosan.web.controller.setting;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import com.samin.dosan.domain.setting.course.CourseType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/course/{type}")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String mainView(@PathVariable String type, @ModelAttribute SearchParam searchParam,
                           Pageable pageable, Model model) {
        Page<Course> result = courseService.findAll(searchParam, CourseType.valueOf(type.toUpperCase()), pageable);
        model.addAttribute("result", result);
        model.addAttribute("courseTypes", CourseType.values());

        return "setting/course/course";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("course") Course saveData) {
        return "setting/course/addForm::#form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);

        return "setting/course/editForm::#form";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 500; i++) {
            Course course = Course.builder()
                    .subject(i + " 과목")
                    .content("수련 소감 및 실천다짐토의 결과 발표")
                    .courseType(CourseType.ENTRY)
                    .used(Used.Y)
                    .build();

            courseService.save(course);
        }
    }
}
