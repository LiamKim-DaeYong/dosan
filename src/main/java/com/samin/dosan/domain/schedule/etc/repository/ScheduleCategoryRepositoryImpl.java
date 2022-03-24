package com.samin.dosan.domain.schedule.etc.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.schedule.etc.ScheduleCategory;
import com.samin.dosan.domain.schedule.etc.ScheduleEtcType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samin.dosan.domain.schedule.etc.QScheduleCategory.scheduleCategory;

@RequiredArgsConstructor
public class ScheduleCategoryRepositoryImpl implements ScheduleCategoryRepositoryQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleCategory> findAllScheduleCategory(ScheduleEtcType scheduleEtcType) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(scheduleCategory.scheduleEtcType.eq(scheduleEtcType));

        List<ScheduleCategory> content = queryFactory
                .selectFrom(scheduleCategory)
                .where(builder)
                .orderBy(scheduleCategory.id.desc())
                .fetch();

        return content;
    }
}
