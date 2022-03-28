package com.samin.dosan.web.controller.educator.training_archive;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.lesson.GradeType;
import com.samin.dosan.domain.training_archive.lesson.Lesson;
import com.samin.dosan.domain.training_archive.lesson.LessonService;
import com.samin.dosan.domain.user.UserService;
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
@RequestMapping("/educator/training-archive/lesson/{type}")
public class LessonController {

    private final LessonService lessonService;
    private final UserService userService;

    @ModelAttribute("gradeTypes")
    public GradeType[] gradeTypes() {
        return GradeType.values();
    }

    @ModelAttribute("gradeType")
    public GradeType gradeType(@PathVariable String type) {
        return GradeType.valueOf(StrUtils.urlToEnumName(type));
    }

    @GetMapping
    public String mainView(@PathVariable String type,  @ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<Lesson> result = lessonService.findAll(searchParam, pageable, type);
        model.addAttribute("result", result);

        return "/educator/training_archive/lesson/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Lesson lesson) {
        return "/educator/training_archive/lesson/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("lesson", lessonService.findById(id));

        return "/educator/training_archive/lesson/detailView";
    }

    @GetMapping("/{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("lesson", lessonService.findById(id));

        return "/educator/training_archive/lesson/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            Lesson lesson = Lesson.builder()
                    .user(userService.findById("educator"))
                    .gradeType(GradeType.ELE_LOWER)
                    .title("제목"+i)
                    .content("내용"+i)
                    .used(Used.Y)
                    .build();

            lessonService.save(lesson);
        }
    }
}
