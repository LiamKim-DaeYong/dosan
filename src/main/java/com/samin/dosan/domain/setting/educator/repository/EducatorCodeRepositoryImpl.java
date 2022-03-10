package com.samin.dosan.domain.setting.educator.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.educator.QEducatorCode.educatorCode;

@RequiredArgsConstructor
public class EducatorCodeRepositoryImpl implements EducatorCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EducatorCode> findAll(SearchParam searchParam, EducatorCodeType educatorCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(educatorCode.educatorCodeType.eq(educatorCodeType).and(educatorCode.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(educatorCode.code.contains(searchWorld));
        }

        List<EducatorCode> content = queryFactory
                .selectFrom(educatorCode)
                .where(builder)
                .orderBy(educatorCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<EducatorCode> countQUery = queryFactory
                .selectFrom(educatorCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQUery.fetch()::size);
    }

    @Override
    public List<EducatorCode> findAllTypes() {
        return queryFactory
                .selectFrom(educatorCode)
                .where(educatorCode.educatorCodeType.eq(EducatorCodeType.TYPE)
                        .and(educatorCode.used.eq(Used.Y)))
                .fetch();
    }
}
