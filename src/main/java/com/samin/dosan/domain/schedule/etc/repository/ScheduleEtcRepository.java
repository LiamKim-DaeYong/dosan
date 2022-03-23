package com.samin.dosan.domain.schedule.etc.repository;

import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleEtcRepository extends JpaRepository<ScheduleEtc, Long>, ScheduleEtcRepositoryQueryDsl {
}
