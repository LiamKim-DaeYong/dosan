package com.samin.dosan.domain.training_archive.branch.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.branch.BranchType;
import com.samin.dosan.domain.training_archive.branch.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.training_archive.branch.QBranch.branch;

@RequiredArgsConstructor
public class BranchRepositoryImpl implements BranchRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    public Page<Branch> findAll(SearchParam searchParam, Pageable pageable, String branchType) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(branch.used.eq(Used.Y)
                .and(branch.branchType.eq(BranchType.valueOf(StrUtils.urlToEnumName(branchType)))));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(branch.title.contains(searchWorld));
        }

        List<Branch> content = queryFactory
                .selectFrom(branch)
                .where(builder)
                .orderBy(branch.noticeYnType.desc(), branch.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Branch> countQuery = queryFactory
                .selectFrom(branch)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
