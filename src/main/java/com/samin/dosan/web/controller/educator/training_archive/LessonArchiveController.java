package com.samin.dosan.web.controller.educator.training_archive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/training_archive/lesson")
public class LessonArchiveController {

//    private final LessonArchiveService lessonArchiveService;

    @GetMapping
    public String mainView() {
        return "/educator/training_archive/lesson/mainView";
    }

    @GetMapping("/detail")
    public String detailView() {
        return "/educator/training_archive/lesson/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/training_archive/lesson/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/training_archive/lesson/editView";
    }
}
