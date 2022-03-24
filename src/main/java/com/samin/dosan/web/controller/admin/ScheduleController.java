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
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/scheduleEtc/{type}")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private ScheduleEtcType scheduleEtcType;

    @GetMapping
    public String mainView(@PathVariable String type, Model model) {
        scheduleEtcType = ScheduleEtcType.valueOf(type.toUpperCase());
        model.addAttribute("scheduleTitle", scheduleEtcType.getDescription());
        return "admin/schedule/mainView";
    }

    @ResponseBody
    @PostMapping("/schduleCategory")
    public List<ScheduleCategory> getSchduleCategory() {
        return scheduleService.getScheduleCategory(scheduleEtcType);
    }

    @ResponseBody
    @PostMapping("/schedule")
    public List<ScheduleEtc> getSchdule() {
        return scheduleService.getSchedule(scheduleEtcType);
    }

    @ResponseBody
    @PutMapping
    public ScheduleEtc saveScheduleEtc(@RequestBody List<ScheduleEtc> scheduleEtcList) {
        return scheduleService.saveScheduleEtc(scheduleEtcList,scheduleEtcType);
    }

    @ResponseBody
    @PutMapping("/updateSchedule")
    public void updateScheduleEtc(@RequestBody List<ScheduleEtc> scheduleEtcList) {
        scheduleService.updateScheduleEtc(scheduleEtcList,scheduleEtcType);
    }

    @ResponseBody
    @DeleteMapping
    public void deleteScheduleEtc(@RequestBody ScheduleEtc scheduleEtc) {
        scheduleService.deleteScheduleEtc(scheduleEtc);
    }

    @PostConstruct
    public void init() {

        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .name("찾아가는 학교수련")
                .color("#ffffff")
                .bgColor("#00a9ff")
                .dragBgColor("#00a9ff")
                .borderColor("#00a9ff")
                .build());


        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .name("수련원 입교수련")
                .color("#ffffff")
                .bgColor("#03bd9e")
                .dragBgColor("#03bd9e")
                .borderColor("#03bd9e")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .name("개인일정")
                .color("#ffffff")
                .bgColor("#613bde")
                .dragBgColor("#613bde")
                .borderColor("#613bde")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("ETC"))
                .name("기타일정")
                .color("#ffffff")
                .bgColor("#000000")
                .dragBgColor("#000000")
                .borderColor("#000000")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("DIRECTOR"))
                .name("원장일정")
                .color("#ffffff")
                .bgColor("#9e5fff")
                .dragBgColor("#9e5fff")
                .borderColor("#9e5fff")
                .build());

        scheduleService.save(ScheduleCategory.builder()
                .scheduleEtcType(ScheduleEtcType.valueOf("CHAIRMAN"))
                .name("이사장일정")
                .color("#ffffff")
                .bgColor("#613bde")
                .dragBgColor("#613bde")
                .borderColor("#613bde")
                .build());


        scheduleService.save(ScheduleEtc.builder()
                .categoryName("찾아가는 학교수련")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(true)
                .location("경주 성남시")
                .title("수학여행")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("수련원 입교수련")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(true)
                .location("서울 강남")
                .title("입교식")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("개인일정")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(false)
                .location("부산 해운대")
                .title("휴가")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.builder()
                .categoryName("기타일정")
                .scheduleEtcType(ScheduleEtcType.valueOf("ETC"))
                .isAllDay(true)
                .location("대구 남구")
                .title("앞산 여행")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());
    }
}
