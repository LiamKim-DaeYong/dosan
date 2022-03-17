package com.samin.dosan.domain.schedule.etc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.schedule.etc.ScheduleCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScheduleCategoryRepositoryImpl implements ScheduleCategoryRepositoryQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleCategory> findAll() {
        return null;
//        BooleanBuilder builder = new BooleanBuilder();
//
//        List<ScheduleCategory> content = queryFactory
//                .selectFrom(scheduleCategory)
//                .where(builder)
//                .orderBy(scheduleCategory.id.desc())
//                .fetch();
//
//        return content;
    }
}
