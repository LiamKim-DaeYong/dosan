package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryQueryDsl {
}
