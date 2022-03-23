package com.samin.dosan.domain.homepage.media_archive.promotional_video.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.media_archive.promotional_video.PromotionalVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.media_archive.promotional_video.QPromotionalVideo.promotionalVideo;

@RequiredArgsConstructor
public class PromotionalVideoRepositoryImpl implements PromotionalVideoRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PromotionalVideo> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(promotionalVideo.used.eq(Used.Y));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            builder.and(promotionalVideo.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(promotionalVideo.regDt.loe(endDate));
        }

        if (searchWorld != null) {
            builder.and(promotionalVideo.title.contains(searchWorld));
        }

        List<PromotionalVideo> content = queryFactory
                .selectFrom(promotionalVideo)
                .where(builder)
                .orderBy(promotionalVideo.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<PromotionalVideo> countQuery = queryFactory
                .selectFrom(promotionalVideo)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
