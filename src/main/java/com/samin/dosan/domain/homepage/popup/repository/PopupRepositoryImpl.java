package com.samin.dosan.domain.homepage.popup.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.popup.Popup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.samin.dosan.domain.homepage.popup.QPopup.popup;

@RequiredArgsConstructor
public class PopupRepositoryImpl implements PopupRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Popup> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(popup.used.eq(Used.Y));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0));
            builder.and(popup.regDt.goe(LocalDate.from(start)));
        }

        if (endDate != null) {
            LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
            builder.and(popup.regDt.loe(LocalDate.from(end)));
        }

        if (searchWorld != null) {
            builder.and(popup.title.contains(searchWorld));
        }

        List<Popup> content = queryFactory
                .selectFrom(popup)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Popup> countQuery = queryFactory
                .selectFrom(popup)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
