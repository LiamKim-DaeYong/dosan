package com.samin.dosan.domain.setting.former.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former.Former;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.former.QFormer.former;

@RequiredArgsConstructor
public class FormerRepositoryImpl implements FormerRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Former> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(former.used.eq(Used.Y));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(former.formerName.contains(searchWord));
        }

        List<Former> content = queryFactory
                .selectFrom(former)
                .where(builder)
                .orderBy(former.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Former> countQuery = queryFactory
                .selectFrom(former)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public List<Former> findByFormerName(Former validFormer) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(former.used.eq(Used.Y)
                .and(former.formerName.eq(validFormer.getFormerName())));

        List<Former> findFormer = queryFactory
                .selectFrom(former)
                .where(builder)
                .fetch();

        return findFormer;
    }

    @Override
    public List<Former> findAllList() {
        return queryFactory
                .selectFrom(former)
                .where(former.used.eq(Used.Y))
                .orderBy(former.formerName.asc())
                .fetch();
    }
}
