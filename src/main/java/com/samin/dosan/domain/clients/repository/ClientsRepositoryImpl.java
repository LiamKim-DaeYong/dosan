package com.samin.dosan.domain.clients.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.Clients;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.clients.QClients.clients;

@RequiredArgsConstructor
public class ClientsRepositoryImpl implements ClientsRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Clients> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(clients.clientNm.contains(searchWorld));
        }

        List<Clients> content = queryFactory
                .selectFrom(clients)
                .where(builder)
                .orderBy(clients.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Clients> countQuery = queryFactory
                .selectFrom(clients)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
