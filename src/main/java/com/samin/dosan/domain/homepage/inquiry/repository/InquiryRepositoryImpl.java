package com.samin.dosan.domain.homepage.inquiry.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.homepage.inquiry.QInquiry.inquiry;

@RequiredArgsConstructor
public class InquiryRepositoryImpl implements InquiryRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Inquiry> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        //        if (filterDto.getFilter() == null && filterDto.getStart() == null && filterDto.getEnd() == null) {
//            builder = new BooleanBuilder();
//        } else {
//            if (filterDto.getFilter() != null && !filterDto.getFilter().equals("")) {
//                builder.and(qInquiry.title.contains(filterDto.getFilter()));
//            }
//
//            if (filterDto.getStart() != null && !filterDto.getStart().equals("")) {
//                builder.and(qInquiry.regDt.goe(LocalDate.parse(filterDto.getStart())));
//            }
//
//            if (filterDto.getEnd() != null && !filterDto.getEnd().equals("")) {
//                builder.and(qInquiry.regDt.loe(LocalDate.parse(filterDto.getEnd())));
//            }
//        }

        List<Inquiry> content = queryFactory
                .selectFrom(inquiry)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(inquiry.id.desc())
                .fetch();

        JPAQuery<Inquiry> countQuery = queryFactory
                .selectFrom(inquiry)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
