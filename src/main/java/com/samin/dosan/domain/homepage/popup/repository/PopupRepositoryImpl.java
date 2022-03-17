package com.samin.dosan.domain.homepage.popup.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.popup.Popup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.homepage.popup.QPopup.popup;

@RequiredArgsConstructor
public class PopupRepositoryImpl implements PopupRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Popup> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

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
