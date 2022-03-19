package com.samin.dosan.domain.setting.former_job_code.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.former_job_code.QFormerCode.formerCode;

@RequiredArgsConstructor
public class FormerJobCodeRepositoryImpl implements FormerJobCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FormerJobCode> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(formerCode.used.eq(Used.Y));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(formerCode.formerNm.contains(searchWord));
        }

        List<FormerJobCode> content = queryFactory
                .selectFrom(formerCode)
                .where(builder)
                .orderBy(formerCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<FormerJobCode> countQuery = queryFactory
                .selectFrom(formerCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public List<FormerJobCode> findByFormerName(FormerJobCode validFormerCode) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(formerCode.used.eq(Used.Y)
                .and(formerCode.formerNm.eq(validFormerCode.getFormerNm())));

        List<FormerJobCode> findFormerCode = queryFactory
                .selectFrom(formerCode)
                .where(builder)
                .fetch();

        return findFormerCode;
    }

    @Override
    public List<FormerJobCode> findAllList() {
        return queryFactory
                .selectFrom(formerCode)
                .where(formerCode.used.eq(Used.Y))
                .orderBy(formerCode.formerNm.asc())
                .fetch();
    }
}