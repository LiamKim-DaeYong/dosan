package com.samin.dosan.domain.schedule.etc;

import com.samin.dosan.domain.schedule.etc.repository.ScheduleCategoryRepository;
import com.samin.dosan.domain.schedule.etc.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleCategoryRepository scheduleCategoryRepository;

    @Transactional
    public List<ScheduleCategory> getScheduleCategory() {
        List<ScheduleCategory> schedules = scheduleCategoryRepository.findAll();
        return scheduleCategoryRepository.findAll();
    }

    public void saveScheduleCategory(List<ScheduleCategory> scheduleCategoryList) {
        for(ScheduleCategory scheduleCategory: scheduleCategoryList) {
            scheduleCategoryRepository.save(scheduleCategory);
        }
    }

    public void save(ScheduleCategory scheduleCategory) {
        scheduleCategoryRepository.save(scheduleCategory);
    }

    public void save(ScheduleEtc scheduleEtc) {
        scheduleRepository.save(scheduleEtc);
    }
}
