package com.samin.dosan.domain.training_archive.operational.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.operational.Operational;
import com.samin.dosan.domain.training_archive.operational.OperationalType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.training_archive.operational.QOperational.operational;

@RequiredArgsConstructor
public class OperationalRepositoryImpl implements OperationalRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Operational> findAll(SearchParam searchParam, Pageable pageable, String type) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(operational.used.eq(Used.Y)
                .and(operational.operationalType.eq(OperationalType.valueOf(StrUtils.urlToEnumName(type)))));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.andAnyOf(
                    operational.title.contains(searchWorld),
                    operational.content.contains(searchWorld)
            );
        }

        List<Operational> content = queryFactory
                .selectFrom(operational)
                .where(builder)
                .orderBy(operational.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Operational> countQuery = queryFactory
                .selectFrom(operational)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
