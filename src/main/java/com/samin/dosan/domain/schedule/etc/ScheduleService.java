package com.samin.dosan.domain.schedule.etc;

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

    @Transactional
    public List<ScheduleEtc> getSchedule(ScheduleEtcType scheduleEtcType) {
        return scheduleEtcRepository.findAllScheduleEtc(scheduleEtcType);
    }

    @Transactional
    public ScheduleEtc save(List<ScheduleEtc> scheduleEtcList, ScheduleEtcType scheduleEtcType) {
        for(ScheduleEtc scheduleEtc: scheduleEtcList) {
            scheduleEtcRepository.save(ScheduleEtc.of(scheduleEtc,scheduleEtcType));
        }

        List<ScheduleEtc> savedScheduleList = scheduleEtcRepository.findAll();
        return savedScheduleList.get(savedScheduleList.size()-1);
    }

    @Transactional
    public void update(List<ScheduleEtc> scheduleEtcList, ScheduleEtcType scheduleEtcType) {
        for(ScheduleEtc scheduleEtc: scheduleEtcList) {
            scheduleEtcRepository.save(ScheduleEtc.of(scheduleEtc,scheduleEtcType));
        }
    }

    @Transactional
    public void delete(ScheduleEtc scheduleEtc) {
        List<ScheduleEtc> scheduleEtcList = scheduleEtcRepository.findAll();
        scheduleEtcRepository.deleteById(scheduleEtc.getId());
    }
    
    public void save(ScheduleEtc scheduleEtc) {
        scheduleEtcRepository.save(scheduleEtc);
    }
}
