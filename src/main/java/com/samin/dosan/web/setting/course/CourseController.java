package com.samin.dosan.web.setting.course;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import com.samin.dosan.domain.type.CourseType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting/course")
public class CourseController {

    private final CourseService courseService;

    @ModelAttribute("courseTypes")
    public CourseType[] courseTypes() {
        return CourseType.values();
    }

    @GetMapping("/{type}")
    public String course(@PathVariable String type,
                         @ModelAttribute SearchParam searchParam,
                         @ModelAttribute("form") CourseForm form,
                         Model model) {
        CourseType courseType = CourseType.valueOf(type.toUpperCase());
        List<Course> courseList = courseService.findAll(searchParam, courseType);

        model.addAttribute("courseList", courseList);
        model.addAttribute("typeEnum", courseType);

        return "setting/course/course";
    }

    @GetMapping("/{type}/add")
    public String addForm(@PathVariable String type, @ModelAttribute("form") CourseForm form) {
        return "setting/course/addForm";
    }

    @ResponseBody
    @PostMapping("/{type}")
    public String save(@PathVariable String type, @Valid @ModelAttribute("form") CourseForm form,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        }

        Course course = Course.builder()
                .subject(form.getSubject())
                .content(form.getContent())
                .courseType(CourseType.valueOf(type.toUpperCase()))
                .build();

        courseService.save(course);

        return "redirect:/setting/course/{type}";
    }
}
