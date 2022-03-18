package com.samin.dosan.domain.setting.subject_code.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.subject_code.SubjectCode;
import com.samin.dosan.domain.setting.subject_code.SubjectCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.subject_code.QSubjectCode.subjectCode;

@RequiredArgsConstructor
public class SubjectCodeRepositoryImpl implements SubjectCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SubjectCode> findAll(SearchParam searchParam, SubjectCodeType subjectCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(subjectCode.subjectCodeType.eq(subjectCodeType).and(subjectCode.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(subjectCode.subject.contains(searchWorld)
                    .or(subjectCode.content.contains(searchWorld)));
        }

        List<SubjectCode> content = queryFactory
                .selectFrom(subjectCode)
                .where(builder)
                .orderBy(subjectCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<SubjectCode> countQuery = queryFactory
                .selectFrom(subjectCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
