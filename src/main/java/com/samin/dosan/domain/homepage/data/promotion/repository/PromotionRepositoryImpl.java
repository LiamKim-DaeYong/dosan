package com.samin.dosan.domain.homepage.data.promotion.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.data.promotion.Promotion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.homepage.data.promotion.QPromotion.promotion;

@RequiredArgsConstructor
public class PromotionRepositoryImpl implements PromotionRepositoryQueryDsl{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Promotion> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        List<Promotion> content = queryFactory
                .selectFrom(promotion)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Promotion> countQuery = queryFactory
                .selectFrom(promotion)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
