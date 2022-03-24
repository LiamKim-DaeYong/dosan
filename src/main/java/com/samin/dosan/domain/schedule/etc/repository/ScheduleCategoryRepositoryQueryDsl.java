package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.ScheduleCategory;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;

import java.util.List;

public interface ScheduleCategoryRepositoryQueryDsl {
    List<ScheduleCategory> findAllScheduleCategory(ScheduleEtcType scheduleEtcType);
}
