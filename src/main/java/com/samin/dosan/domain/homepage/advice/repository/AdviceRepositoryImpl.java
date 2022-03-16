package com.samin.dosan.domain.homepage.advice.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.advice.Advice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.advice.QAdvice.advice;

@RequiredArgsConstructor
public class AdviceRepositoryImpl implements AdviceRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Advice> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchWord = searchParam.getSearchWorld();
        String selectKey = searchParam.getSelectKey();
        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();

        if (startDate == null && endDate == null) {
            builder.and(advice.regDt.eq(LocalDate.now()));
        }

        if (selectKey != null) {
            switch (selectKey) {
                case "all":
                    break;
                case "sunbi":
                    builder.and(advice.adviceType.eq("찾아가는 선비체험"));
                    break;
                case "admission":
                    builder.and(advice.adviceType.eq("입교과정"));
                    break;
                case "family":
                    builder.and(advice.adviceType.eq("가족"));
                    break;
                case "ordinary":
                    builder.and(advice.adviceType.eq("일반인"));
                    break;
                case "uncheck":
                    builder.and(advice.status.eq("N"));
                    break;
                case "check":
                    builder.and(advice.status.eq("Y"));
                    break;
            }
        }

        if (searchWord != null) {
            builder.and(advice.applicant.contains(searchWord))
                    .or(advice.insttNm.contains(searchWord))
                    .or(advice.depart.contains(searchWord));
        }

        List<Advice> content = queryFactory
                .selectFrom(advice)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return null;
    }
}
