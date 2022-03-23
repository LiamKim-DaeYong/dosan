package com.samin.dosan.domain.schedule.etc;

import com.samin.dosan.domain.schedule.etc.repository.ScheduleCategoryRepository;
import com.samin.dosan.domain.schedule.etc.repository.ScheduleEtcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleEtcRepository scheduleEtcRepository;

    private final ScheduleCategoryRepository scheduleCategoryRepository;

    @Transactional
    public List<ScheduleCategory> getScheduleCategory() {
        return scheduleCategoryRepository.findAllScheduleCategory();
    }

    @Transactional
    public List<ScheduleEtc> getSchedule() {
        List<ScheduleEtc> schedules = scheduleEtcRepository.findAllScheduleEtc();
        return scheduleEtcRepository.findAllScheduleEtc();
    }

    public void saveScheduleCategory(List<ScheduleCategory> scheduleCategoryList) {
        for(ScheduleCategory scheduleCategory: scheduleCategoryList) {
            scheduleCategoryRepository.save(scheduleCategory);
        }
    }

    public void saveScheduleEtc(List<ScheduleEtc> scheduleEtcList) {
        for(ScheduleEtc scheduleEtc: scheduleEtcList) {
            scheduleEtcRepository.save(scheduleEtc);
        }
    }

    public void save(ScheduleCategory scheduleCategory) {
        scheduleCategoryRepository.save(scheduleCategory);
    }

    public void save(ScheduleEtc scheduleEtc) {
        scheduleEtcRepository.save(scheduleEtc);
    }
}
