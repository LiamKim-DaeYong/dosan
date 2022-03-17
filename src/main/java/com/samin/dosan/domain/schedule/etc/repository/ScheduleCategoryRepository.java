package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.ScheduleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, Long>, ScheduleCategoryRepositoryQueryDsl {
}
