package com.samin.dosan.domain.homepage.main_image.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.main_image.MainImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.main_image.QMainImage.mainImage;

@RequiredArgsConstructor
public class MainImageRepositoryImpl implements MainImageRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MainImage> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (searchWorld != null) {
            builder.and(mainImage.title.contains(searchWorld));
        }

        List<MainImage> content = queryFactory
                .selectFrom(mainImage)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<MainImage> countQuery = queryFactory
                .selectFrom(mainImage)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
