package com.samin.dosan.domain.homepage.training_inquiry.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import com.samin.dosan.domain.homepage.type.CheckType;
import com.samin.dosan.domain.homepage.type.TrainingType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.samin.dosan.domain.homepage.training_inquiry.QTrainingInquiry.trainingInquiry;

@RequiredArgsConstructor
public class TrainingInquiryRepositoryImpl implements TrainingInquiryRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TrainingInquiry> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(trainingInquiry.used.eq(Used.Y));

        String searchWord = searchParam.getSearchWorld();
        String selectKey = searchParam.getSelectKey();
        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();

        if (startDate == null && endDate == null) {
            builder.and(trainingInquiry.regDt.eq(LocalDate.now()));
        }

        if (startDate != null) {
            builder.and(trainingInquiry.regDt.goe(startDate));
        }

        if (endDate != null) {
            builder.and(trainingInquiry.regDt.loe(endDate));
        }

        if (selectKey != null) {
            switch (selectKey) {
                case "ALL":
                    break;
                case "SUNBI": case "ADMISSION": case "FAMILY": case "ORDINARY":
                    builder.and(trainingInquiry.trainingInquiryType.eq(TrainingType.valueOf(selectKey)));
                    break;
                case "UNCHECK":
                    builder.and(trainingInquiry.status.isNull());
                    break;
                case "CHECK":
                    builder.and(trainingInquiry.status.eq(CheckType.Y));
                    break;
            }
        }

        if (searchWord != null && !searchWord.trim().equals("")) {
            builder.andAnyOf(
                    trainingInquiry.applicant.contains(searchWord),
                    trainingInquiry.insttNm.contains(searchWord),
                    trainingInquiry.depart.contains(searchWord)
                    );
        }

        List<TrainingInquiry> content = queryFactory
                .selectFrom(trainingInquiry)
                .where(builder)
                .orderBy(trainingInquiry.status.desc(), trainingInquiry.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<TrainingInquiry> countQuery = queryFactory
                .selectFrom(trainingInquiry)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
