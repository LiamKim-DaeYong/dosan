package com.samin.dosan.domain.homepage.training_review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_review.TrainingReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.training_review.QTrainingReview.trainingReview;

@RequiredArgsConstructor
public class TrainingReviewRepositoryImpl implements TrainingReviewRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TrainingReview> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(trainingReview.used.eq(Used.Y));

        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String searchWorld = searchParam.getSearchWorld();

        if (startDate != null) {
            builder.and(trainingReview.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(trainingReview.regDt.loe(endDate));
        }

        if (searchWorld != null) {
            builder.and(trainingReview.title.contains(searchWorld));
        }

        List<TrainingReview> content = queryFactory
                .selectFrom(trainingReview)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<TrainingReview> countQuery = queryFactory
                .selectFrom(trainingReview)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
