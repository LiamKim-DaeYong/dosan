package com.samin.dosan.domain.setting.place_code.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.PlaceCode;
import com.samin.dosan.domain.setting.place_code.PlaceCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.place_code.QPlaceCode.placeCode;

@RequiredArgsConstructor
public class PlaceCodeRepositoryImpl implements PlaceCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PlaceCode> findAll(SearchParam searchParam, PlaceCodeType placeCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(placeCode.placeCodeType.eq(placeCodeType).and(placeCode.used.eq(Used.Y)));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(placeCode.placeNm.contains(searchWord));
        }

        List<PlaceCode> content = queryFactory
                .selectFrom(placeCode)
                .where(builder)
                .orderBy(placeCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<PlaceCode> countQuery = queryFactory
                .selectFrom(placeCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public List<PlaceCode> findByLocation(PlaceCodeType placeCodeType, PlaceCode validPlaceCode) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(placeCode.placeCodeType.eq(placeCodeType)
                .and(placeCode.used.eq(Used.Y))
                .and(placeCode.placeNm.eq(validPlaceCode.getPlaceNm())));

        List<PlaceCode> findPlaceCode = queryFactory
                .selectFrom(placeCode)
                .where(builder)
                .fetch();

        return findPlaceCode;
    }
}
