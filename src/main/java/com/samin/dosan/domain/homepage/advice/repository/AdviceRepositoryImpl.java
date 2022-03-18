package com.samin.dosan.domain.homepage.advice.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.Advice;
import com.samin.dosan.domain.homepage.advice.AdviceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.advice.QAdvice.advice;

@RequiredArgsConstructor
public class AdviceRepositoryImpl implements AdviceRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Advice> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(advice.used.eq(Used.Y));

        String searchWord = searchParam.getSearchWorld();
        String selectKey = searchParam.getSelectKey();
        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();

        if (startDate == null && endDate == null) {
            builder.and(advice.regDt.eq(LocalDate.now()));
        }

        if (startDate != null) {
            builder.and(advice.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(advice.regDt.loe(endDate));
        }

        if (selectKey != null) {
            switch (selectKey) {
                case "ALL":
                    break;
                case "SUNBI": case "ADMISSION": case "FAMILY": case "ORDINARY":
                    builder.and(advice.adviceType.eq(AdviceType.valueOf(selectKey)));
                    break;
                case "UNCHECK":
                    builder.and(advice.status.isNull());
                    break;
                case "CHECK":
                    builder.and(advice.status.eq("Y"));
                    break;
            }
        }

        if (searchWord != null && !searchWord.trim().equals("")) {
            builder.andAnyOf(
                    advice.applicant.contains(searchWord),
                    advice.insttNm.contains(searchWord),
                    advice.depart.contains(searchWord)
                    );
        }

        List<Advice> content = queryFactory
                .selectFrom(advice)
                .where(builder)
                .orderBy(advice.status.desc(), advice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Advice> countQuery = queryFactory
                .selectFrom(advice)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
