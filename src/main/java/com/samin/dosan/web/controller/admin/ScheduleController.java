package com.samin.dosan.web.controller.admin;


import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;
import com.samin.dosan.domain.schedule.etc.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

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

    @PostConstruct
    public void init() {

        scheduleService.save(ScheduleEtc.test()
                .categoryNm("찾아가는 학교수련")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(true)
                .location("경주 성남시")
                .title("수학여행")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.test()
                .categoryNm("수련원 입교수련")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(true)
                .location("서울 강남")
                .title("입교식")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.test()
                .categoryNm("개인일정")
                .scheduleEtcType(ScheduleEtcType.valueOf("NONE"))
                .isAllDay(false)
                .location("부산 해운대")
                .title("휴가")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());

        scheduleService.save(ScheduleEtc.test()
                .categoryNm("기타일정")
                .scheduleEtcType(ScheduleEtcType.valueOf("ETC"))
                .isAllDay(true)
                .location("대구 남구")
                .title("앞산 여행")
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build());
    }
}
