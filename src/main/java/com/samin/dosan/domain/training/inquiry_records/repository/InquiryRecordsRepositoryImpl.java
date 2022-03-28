package com.samin.dosan.domain.training.inquiry_records.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.clients.QClients.clients;
import static com.samin.dosan.domain.training.inquiry_records.QTrainingInquiryRecords.trainingInquiryRecords;


@RequiredArgsConstructor
public class InquiryRecordsRepositoryImpl implements InquiryRecordsRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TrainingInquiryRecords> findAll(SearchParam searchParam, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(trainingInquiryRecords.clients.clientNm.contains(searchWorld));
        }

        List<TrainingInquiryRecords> content = queryFactory
                .selectFrom(trainingInquiryRecords)
                .leftJoin(trainingInquiryRecords.clients, clients).fetchJoin()
                .where(builder)
                .orderBy(trainingInquiryRecords.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<TrainingInquiryRecords> countQuery = queryFactory
                .selectFrom(trainingInquiryRecords)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
