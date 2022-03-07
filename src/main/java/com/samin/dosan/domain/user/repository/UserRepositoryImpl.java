package com.samin.dosan.domain.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryQueryDSL {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<User> findAllManager(SearchParam searchParam, ManagerType managerType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (managerType != null) {
        }


        return null;
    }
}
