package com.samin.dosan.domain.homepage.data.webtoon.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.homepage.data.webtoon.QWebtoon.webtoon;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Webtoon> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        List<Webtoon> content = queryFactory
                .selectFrom(webtoon)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Webtoon> countQuery = queryFactory
                .selectFrom(webtoon)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
