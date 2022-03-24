package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;

import java.util.List;

public interface ScheduleEtcRepositoryQueryDsl {
    List<ScheduleEtc> findAllScheduleEtc(ScheduleEtcType scheduleEtcType);
}
