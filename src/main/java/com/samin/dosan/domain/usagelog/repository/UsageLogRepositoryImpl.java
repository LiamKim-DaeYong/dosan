package com.samin.dosan.domain.usagelog.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.usagelog.UsageLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.usagelog.QUsageLog.usageLog;

@RequiredArgsConstructor
public class UsageLogRepositoryImpl implements UsageLogRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UsageLog> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(usageLog.systemNm.contains(searchWorld)
                    .or(usageLog.method.contains(searchWorld)));
        }

        List<UsageLog> content = queryFactory
                .selectFrom(usageLog)
                .where(builder)
                .orderBy(usageLog.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<UsageLog> countQuery = queryFactory
                .selectFrom(usageLog)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
