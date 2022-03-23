package com.samin.dosan.domain.homepage.qna.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.qna.Qna;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.qna.QQna.qna;

@RequiredArgsConstructor
public class QnaRepositoryImpl implements QnaRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Qna> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qna.used.eq(Used.Y));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            builder.and(qna.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(qna.regDt.loe(endDate));
        }

        if (searchWorld != null) {
            builder.and(qna.title.contains(searchWorld));
        }

        List<Qna> content = queryFactory
                .selectFrom(qna)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Qna> countQuery = queryFactory
                .selectFrom(qna)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
