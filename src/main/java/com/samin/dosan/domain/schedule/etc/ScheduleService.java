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
    public List<ScheduleCategory> getScheduleCategory(ScheduleEtcType scheduleEtcType) {
        return scheduleCategoryRepository.findAllScheduleCategory(scheduleEtcType);
    }

    @Transactional
    public List<ScheduleEtc> getSchedule(ScheduleEtcType scheduleEtcType) {
        return scheduleEtcRepository.findAllScheduleEtc(scheduleEtcType);
    }

    @Transactional
    public ScheduleEtc saveScheduleEtc(List<ScheduleEtc> scheduleEtcList, ScheduleEtcType scheduleEtcType) {
        for(ScheduleEtc scheduleEtc: scheduleEtcList) {
            scheduleEtcRepository.save(ScheduleEtc.builder()
                    .categoryName(scheduleEtc.getCategoryName())
                    .scheduleEtcType(scheduleEtcType)
                    .isAllDay(scheduleEtc.getIsAllDay())
                    .location(scheduleEtc.getLocation())
                    .title(scheduleEtc.getTitle())
                    .start(scheduleEtc.getStart())
                    .end(scheduleEtc.getEnd())
                    .build());
        }

        List<ScheduleEtc> savedScheduleList = scheduleEtcRepository.findAll();
        return savedScheduleList.get(savedScheduleList.size()-1);
    }

    @Transactional
    public void updateScheduleEtc(List<ScheduleEtc> scheduleEtcList, ScheduleEtcType scheduleEtcType) {
        List<ScheduleEtc> scheduleEtcList1 = scheduleEtcRepository.findAll();
        for(ScheduleEtc scheduleEtc: scheduleEtcList) {
            scheduleEtcRepository.save(ScheduleEtc.builder()
                    .scheduleId(scheduleEtc.getScheduleId())
                    .categoryName(scheduleEtc.getCategoryName())
                    .scheduleEtcType(scheduleEtcType)
                    .isAllDay(scheduleEtc.getIsAllDay())
                    .location(scheduleEtc.getLocation())
                    .title(scheduleEtc.getTitle())
                    .start(scheduleEtc.getStart())
                    .end(scheduleEtc.getEnd())
                    .build());
        }
    }

    @Transactional
    public void deleteScheduleEtc(ScheduleEtc scheduleEtc) {
        List<ScheduleEtc> scheduleEtcList = scheduleEtcRepository.findAll();
        scheduleEtcRepository.deleteById(scheduleEtc.getScheduleId());
    }

    public void save(ScheduleCategory scheduleCategory) {
        scheduleCategoryRepository.save(scheduleCategory);
    }

    public void save(ScheduleEtc scheduleEtc) {
        scheduleEtcRepository.save(scheduleEtc);
    }
}
