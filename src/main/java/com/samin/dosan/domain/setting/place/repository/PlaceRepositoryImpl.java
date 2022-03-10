package com.samin.dosan.domain.setting.place.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place.Place;
import com.samin.dosan.domain.setting.place.PlaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.place.QPlace.place;

@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Place> findAll(SearchParam searchParam, PlaceType placeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(place.placeType.eq(placeType).and(place.used.eq(Used.Y)));

        String searchWord = searchParam.getSearchWorld();
        if (searchWord != null) {
            builder.and(place.location.contains(searchWord));
        }

        List<Place> content = queryFactory
                .selectFrom(place)
                .where(builder)
                .orderBy(place.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Place> countQuery = queryFactory
                .selectFrom(place)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public List<Place> findByLocation(PlaceType placeType, Place validPlace) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(place.placeType.eq(placeType)
                .and(place.used.eq(Used.Y))
                .and(place.location.eq(validPlace.getLocation())));

        List<Place> findPlace = queryFactory
                .selectFrom(place)
                .where(builder)
                .fetch();

        return findPlace;
    }
}
