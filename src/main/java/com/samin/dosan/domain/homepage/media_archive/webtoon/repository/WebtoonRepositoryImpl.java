package com.samin.dosan.domain.homepage.media_archive.webtoon.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.media_archive.webtoon.QWebtoon.webtoon;

@RequiredArgsConstructor
public class WebtoonRepositoryImpl implements WebtoonRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Webtoon> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(webtoon.used.eq(Used.Y));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            builder.and(webtoon.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(webtoon.regDt.loe(endDate));
        }

        if (searchWorld != null) {
            builder.and(webtoon.title.contains(searchWorld));
        }

        List<Webtoon> content = queryFactory
                .selectFrom(webtoon)
                .where(builder)
                .orderBy(webtoon.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Webtoon> countQuery = queryFactory
                .selectFrom(webtoon)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
