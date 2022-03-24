package com.samin.dosan.domain.business_trip_report.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.business_trip_report.BusinessTripReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.business_trip_report.QBusinessTripReport.businessTripReport;

@RequiredArgsConstructor
public class BusinessTripRepositoryImpl implements BusinessTripRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BusinessTripReport> findAll(SearchParam searchParam, Pageable pageable, String username) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(businessTripReport.used.eq(Used.Y)
                .and(businessTripReport.user.userId.eq(username)));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(businessTripReport.title.contains(searchWord));
        }

        List<BusinessTripReport> content = queryFactory
                .selectFrom(businessTripReport)
                .where(builder)
                .orderBy(businessTripReport.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<BusinessTripReport> countQuery = queryFactory
                .selectFrom(businessTripReport)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
