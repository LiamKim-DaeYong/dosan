package com.samin.dosan.web.controller.schedule;

import com.samin.dosan.domain.schedule.ScheduleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule/{type}")
public class ScheduleController {

    @GetMapping
    public String mainView(@PathVariable String type, Model model) {
        ScheduleType scheduleType = ScheduleType.valueOf(type.toUpperCase());

        model.addAttribute("scheduleTitle", scheduleType.getDescription());

        return "schedule/schedule";
    }
}
