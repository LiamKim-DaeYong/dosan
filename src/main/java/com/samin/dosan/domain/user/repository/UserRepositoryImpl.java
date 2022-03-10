package com.samin.dosan.domain.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryQueryDSL {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<User> findAllEmployees(SearchParam searchParam, Long employeesType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchWorld = searchParam.getSearchWorld();
        builder.and(user.role.eq("ROLE_MANAGER"));

        if (searchWorld != null) {
            builder.and(user.userNm.contains(searchWorld));
        }

        if (employeesType != null) {
            builder.and(user.employeesType.id.eq(employeesType));
        }

        List<User> content = queryFactory
                .selectFrom(user)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<User> countQuery = queryFactory
                .selectFrom(user)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public Page<User> findAllEducators(SearchParam searchParam, Long educatorType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchWorld = searchParam.getSearchWorld();
        builder.and(user.role.eq("ROLE_USER"));

        if (searchWorld != null) {
            builder.and(user.userNm.contains(searchWorld));
        }

        if (educatorType != null) {
            builder.and(user.educatorType.id.eq(educatorType));
        }

        List<User> content = queryFactory
                .selectFrom(user)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<User> countQuery = queryFactory
                .selectFrom(user)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
