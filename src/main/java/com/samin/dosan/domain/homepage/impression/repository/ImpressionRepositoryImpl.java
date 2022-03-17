package com.samin.dosan.domain.homepage.impression.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.impression.Impression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.homepage.impression.QImpression.impression;

@RequiredArgsConstructor
public class ImpressionRepositoryImpl implements ImpressionRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Impression> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

//        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
//            builder = new BooleanBuilder();
//        } else {
//            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
//                builder.and(qImpression.title.contains(filterDto.getFilter()));
//            }
//
//            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
//                builder.and(qImpression.regDt.goe(LocalDate.parse(filterDto.getStart())));
//            }
//
//            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
//                builder.and(qImpression.regDt.loe(LocalDate.parse(filterDto.getEnd())));
//            }
//        }

        List<Impression> content = queryFactory
                .selectFrom(impression)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(impression.id.desc())
                .fetch();

        JPAQuery<Impression> countQuery = queryFactory
                .selectFrom(impression)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
