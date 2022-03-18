package com.samin.dosan.web.controller.admin;

import com.samin.dosan.domain.schedule.etc.ScheduleCategory;
import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;
import com.samin.dosan.domain.schedule.etc.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/schedule/{type}")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public String mainView(@PathVariable String type, Model model) {
        ScheduleEtcType scheduleEtcType = ScheduleEtcType.valueOf(type.toUpperCase());
        model.addAttribute("scheduleTitle", scheduleEtcType.getDescription());
        model.addAttribute("scheduleCategoryList",scheduleService.getScheduleCategory());
        return "admin/schedule";
    }

    @ResponseBody
    @GetMapping("/schduleCategory")
    public List<ScheduleCategory> getSchduleCategory() {
        return scheduleService.getScheduleCategory();

    }


    @PutMapping
    public void saveScheduleCategory(@Valid @RequestBody List<ScheduleCategory> scheduleCategoryList) {
        scheduleService.saveScheduleCategory(scheduleCategoryList);
    }

    @PostConstruct
    public void init() {
        scheduleService.save(ScheduleCategory.builder()
                .id(1L)
                .name("찾아가는 학교수련")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .id(2L)
                .name("수련원 입교수련")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .id(3L)
                .name("개인일정")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .id(4L)
                .name("기타일정")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .id(1L)
                .calendarId("1")
                .location("test")
                .title("sdsdsdsd")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build());

    }
}
