package com.samin.dosan.web.controller.educator.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/schedule/training")
public class ScheduleTrainingController {

    @GetMapping
    public String mainView() {
        return "/educator/schedule/training/mainView";
    }
}
