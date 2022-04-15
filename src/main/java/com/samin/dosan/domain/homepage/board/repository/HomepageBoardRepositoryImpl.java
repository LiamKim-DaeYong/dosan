package com.samin.dosan.domain.homepage.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.board.HomepageBoard;
import com.samin.dosan.domain.homepage.type.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.samin.dosan.domain.homepage.board.QHomepageBoard.homepageBoard;

@RequiredArgsConstructor
public class HomepageBoardRepositoryImpl implements HomepageBoardRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<HomepageBoard> findAll(SearchParam searchParam, Pageable pageable, BoardType boardType) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(homepageBoard.used.eq(Used.Y)
                .and(homepageBoard.boardType.eq(boardType)));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            builder.and(homepageBoard.createdAt.goe(LocalDateTime.of(startDate, LocalTime.of(0, 0, 0))));
        }

        if (endDate != null) {
            builder.and(homepageBoard.createdAt.loe(LocalDateTime.of(endDate, LocalTime.of(23, 59, 59))));
        }

        if (searchWorld != null) {
            builder.and(homepageBoard.title.contains(searchWorld));
        }

        List<HomepageBoard> content = queryFactory
                .selectFrom(homepageBoard)
                .where(builder)
                .orderBy(homepageBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<HomepageBoard> countQuery = queryFactory
                .selectFrom(homepageBoard)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
