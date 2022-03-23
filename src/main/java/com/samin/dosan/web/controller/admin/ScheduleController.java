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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/scheduleEtc/{type}")
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
    @PostMapping("/schduleCategory")
    public List<ScheduleCategory> getSchduleCategory() {
        return scheduleService.getScheduleCategory();
    }

    @ResponseBody
    @PostMapping("/schedule")
    public List<ScheduleEtc> getSchdule() {
        return scheduleService.getSchedule();
    }

    @PutMapping("/scheduleCategory")
    public void saveScheduleCategory(@RequestBody List<ScheduleCategory> scheduleCategoryList) {
        scheduleService.saveScheduleCategory(scheduleCategoryList);
    }

    @PutMapping("/etcSchedule")
    public void saveScheduleEtc(@RequestBody List<ScheduleEtc> scheduleEtcList) {
        scheduleService.saveScheduleEtc(scheduleEtcList);
    }

    @PostConstruct
    public void init() {

        scheduleService.save(ScheduleCategory.builder()
                .name("찾아가는 학교수련")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());


        scheduleService.save(ScheduleCategory.builder()
                .name("수련원 입교수련")
                .color("#ffffff")
                .bgColor("#03bd9e")
                .dragBgColor("#03bd9e")
                .borderColor("#03bd9e")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .name("개인일정")
                .color("#ffffff")
                .bgColor("#9e5fff")
                .dragBgColor("#9e5fff")
                .borderColor("#9e5fff")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .name("기타일정")
                .color("#ffffff")
                .bgColor("#000000")
                .dragBgColor("#000000")
                .borderColor("#000000")
                .build());


        scheduleService.save(ScheduleEtc.builder()
                .categoryName("찾아가는 학교수련")
                .isAllDay(true)
                .location("경주 성남시")
                .title("수학여행")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("수련원 입교수련")
                .isAllDay(true)
                .location("서울 강남")
                .title("입교식")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("개인일정")
                .isAllDay(false)
                .location("부산 해운대")
                .title("휴가")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("기타일정")
                .isAllDay(true)
                .location("대구 남구")
                .title("앞산 여행")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build());
    }
}
