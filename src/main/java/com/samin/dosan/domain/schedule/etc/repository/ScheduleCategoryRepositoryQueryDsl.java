package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.ScheduleCategory;

import java.util.List;

public interface ScheduleCategoryRepositoryQueryDsl {
    List<ScheduleCategory> findAllScheduleCategory();
}
