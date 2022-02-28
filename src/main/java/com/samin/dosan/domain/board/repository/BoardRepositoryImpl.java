package com.samin.dosan.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.board.Board;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samin.dosan.domain.board.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryQueryDSL {

    private final JPAQueryFactory queryFactory;

    public List<Board> findAll(SearchParam searchParam) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchWorld = searchParam.getSearchWorld();

        if (searchWorld != null) {
            builder.and(board.title.contains(searchWorld));
        }

        return queryFactory.selectFrom(board)
                .where(builder)
                .fetch();
    }
}
