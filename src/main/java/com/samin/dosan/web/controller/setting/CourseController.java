package com.samin.dosan.web.controller.setting;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseService;
import com.samin.dosan.domain.type.setting.CourseType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String mainView(@PathVariable String type,
                           @ModelAttribute SearchParam searchParam, Model model) {
        List<Course> courseList = courseService.findAll(searchParam, CourseType.valueOf(type.toUpperCase()));
        model.addAttribute("courseList", courseList);

        return "setting/course/course";
    }

    @GetMapping("/{type}/add")
    public String addForm(@PathVariable String type, @ModelAttribute Course course) {
        course.setCourseType(type);
        return "setting/course/addForm::#form";
    }

    @GetMapping("/{type}/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);

        return "setting/course/editForm::#form";
    }
}
