package com.samin.dosan.web.api;

import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;
import com.samin.dosan.domain.schedule.etc.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/scheduleEtc/{type}")
public class ScheduleApiController {

    private final ScheduleService scheduleService;
    private ScheduleEtcType scheduleEtcType;

    @PostMapping("/schduleCategory")
    public ResponseEntity getSchduleCategory(@PathVariable String type) {
        scheduleEtcType = ScheduleEtcType.valueOf(type.toUpperCase());
        return ResponseEntity.ok(scheduleEtcType.name());
    }

    @PostMapping("/schedule")
    public ResponseEntity getSchdule(@PathVariable String type) {
        scheduleEtcType = ScheduleEtcType.valueOf(type.toUpperCase());
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleEtcType));
    }

    @PutMapping
    public ResponseEntity save(@RequestBody List<ScheduleEtc> scheduleEtcList) {
        return  ResponseEntity.ok(scheduleService.save(scheduleEtcList,scheduleEtcType));
    }

    @PutMapping("/updateSchedule")
    public ResponseEntity update(@RequestBody List<ScheduleEtc> scheduleEtcList) {
        scheduleService.update(scheduleEtcList,scheduleEtcType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void delete(@RequestBody ScheduleEtc scheduleEtc) {
        scheduleService.delete(scheduleEtc);
    }

}
