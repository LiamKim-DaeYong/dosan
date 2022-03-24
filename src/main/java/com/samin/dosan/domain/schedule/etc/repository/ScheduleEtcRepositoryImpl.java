package com.samin.dosan.domain.schedule.etc.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.schedule.etc.ScheduleEtc;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samin.dosan.domain.schedule.etc.QScheduleEtc.scheduleEtc;


@RequiredArgsConstructor
public class ScheduleEtcRepositoryImpl implements ScheduleEtcRepositoryQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleEtc> findAllScheduleEtc(ScheduleEtcType scheduleEtcType) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(scheduleEtc.scheduleEtcType.eq(scheduleEtcType));

        List<ScheduleEtc> content = queryFactory
                .selectFrom(scheduleEtc)
                .where(builder)
                .orderBy(scheduleEtc.id.desc())
                .fetch();

        return content;
    }
}
