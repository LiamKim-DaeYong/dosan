package com.samin.dosan.domain.training_archive.consultation.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.consultation.Consultation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.training_archive.consultation.QConsultation.consultation;

@RequiredArgsConstructor
public class ConsultationRepositoryImpl implements ConsultationRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Consultation> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(consultation.used.eq(Used.Y));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.andAnyOf(
                    consultation.title.contains(searchWorld),
                    consultation.content.contains(searchWorld)
            );
        }

        List<Consultation> content = queryFactory
                .selectFrom(consultation)
                .where(builder)
                .orderBy(consultation.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Consultation> countQuery = queryFactory
                .selectFrom(consultation)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
