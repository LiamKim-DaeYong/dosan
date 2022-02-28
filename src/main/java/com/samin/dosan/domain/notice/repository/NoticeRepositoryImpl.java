package com.samin.dosan.domain.notice.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.notice.Notice;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samin.dosan.domain.notice.QNotice.notice;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryQueryDSL {

    private final JPAQueryFactory queryFactory;

    public List<Notice> findAll(SearchParam searchParam) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchWord = searchParam.getSearchWorld();

        if (searchWord != null) {
            builder.and(notice.title.contains(searchWord));
        }

        return queryFactory.selectFrom(notice)
                .where(builder)
                .fetch();
    }
}
