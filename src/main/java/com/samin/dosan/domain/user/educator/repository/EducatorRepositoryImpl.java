package com.samin.dosan.domain.user.educator.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.educator.entity.Educator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.educator_code.QEducatorCode.educatorCode;
import static com.samin.dosan.domain.user.educator.entity.QEducator.educator;
import static com.samin.dosan.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class EducatorRepositoryImpl implements EducatorRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Educator> findAll(SearchParam searchParam, Long educatorCodeId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(educator.user.used.eq(Used.Y));

        if (educatorCodeId != null) {
            builder.and(educator.educatorType.id.eq(educatorCodeId));
        }
        String searchWorld = searchParam.getSearchWorld();

        if (searchWorld != null) {
            builder.and(educator.user.userNm.contains(searchWorld)

                    .or(educator.educatorType.code.contains(searchWorld))
                    .or(educator.educatorAssignedTask.code.contains(searchWorld))
                    .or(educator.educatorTeam.code.contains(searchWorld))
                    .or(educator.educatorBranch.code.contains(searchWorld))
                    .or(educator.phoneNum.contains(searchWorld))
            );
        }

        List<Educator> content = queryFactory
                .selectFrom(educator)
                .leftJoin(educator.user, user).fetchJoin()
                .leftJoin(educator.educatorType, educatorCode).fetchJoin()
                .leftJoin(educator.educatorAssignedTask, educatorCode).fetchJoin()
                .leftJoin(educator.educatorTeam, educatorCode).fetchJoin()
                .leftJoin(educator.educatorBranch, educatorCode).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Educator> countQuery = queryFactory
                .selectFrom(educator)
                .leftJoin(educator.user, user).fetchJoin()
                .leftJoin(educator.educatorType, educatorCode).fetchJoin()
                .leftJoin(educator.educatorAssignedTask, educatorCode).fetchJoin()
                .leftJoin(educator.educatorTeam, educatorCode).fetchJoin()
                .leftJoin(educator.educatorBranch, educatorCode).fetchJoin()
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public Educator findByUserId(String userId) {
        return queryFactory
                .selectFrom(educator)
                .leftJoin(educator.user, user).fetchJoin()
                .leftJoin(educator.educatorType, educatorCode).fetchJoin()
                .leftJoin(educator.educatorAssignedTask, educatorCode).fetchJoin()
                .leftJoin(educator.educatorTeam, educatorCode).fetchJoin()
                .leftJoin(educator.educatorBranch, educatorCode).fetchJoin()
                .where(educator.user.userId.eq(userId))
                .fetchOne();
    }
}
