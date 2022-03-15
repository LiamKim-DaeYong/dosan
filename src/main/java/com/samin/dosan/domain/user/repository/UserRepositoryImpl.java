package com.samin.dosan.domain.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.User;
import com.samin.dosan.domain.user.UserType;
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
        builder.and(user.used.eq(Used.Y).and(user.userType.eq(UserType.EMPLOYEES)));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(user.userId.contains(searchWord))
                    .or(user.userNm.contains(searchWord));
        }

        if (employeesType != null) {
            builder.and(user.employeesType.id.eq(employeesType));
        }

        List<User> content = queryFactory
                .selectFrom(user)
                .where(builder)
                .orderBy(user.userId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<User> countQuery = queryFactory
                .selectFrom(user)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public boolean existById(String userId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.userId.eq(userId));

        User findUser = queryFactory
                .selectFrom(user)
                .where(builder)
                .fetchOne();

        return findUser != null;
    }
}
