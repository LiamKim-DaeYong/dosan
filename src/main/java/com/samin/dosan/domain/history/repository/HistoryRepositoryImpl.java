package com.samin.dosan.domain.history.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.history.History;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.history.QHistory.history;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<History> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(history.systemNm.contains(searchWorld)
                    .or(history.method.contains(searchWorld)));
        }

        List<History> content = queryFactory
                .selectFrom(history)
                .where(builder)
                .orderBy(history.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<History> countQuery = queryFactory
                .selectFrom(history)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
