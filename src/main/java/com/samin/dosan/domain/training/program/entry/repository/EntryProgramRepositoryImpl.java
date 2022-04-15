package com.samin.dosan.domain.training.program.entry.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.place_code.QPlaceCode;
import com.samin.dosan.domain.training.program.entry.entity.EntryProgram;
import com.samin.dosan.web.dto.training.program.entry.EntryProgramList;
import com.samin.dosan.web.dto.training.program.entry.QEntryProgramList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.clients.QClients.clients;
import static com.samin.dosan.domain.setting.place_code.QPlaceCode.placeCode;
import static com.samin.dosan.domain.training.inquiry_records.QInquiryRecords.inquiryRecords;
import static com.samin.dosan.domain.training.program.entry.entity.QEntryProgram.entryProgram;
import static com.samin.dosan.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class EntryProgramRepositoryImpl implements EntryProgramRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EntryProgramList> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        List<EntryProgramList> content = queryFactory
                .select(new QEntryProgramList(
                    entryProgram.id,
                    entryProgram.inquiryRecords.clients.clientNm,
                    entryProgram.inquiryRecords.trainingStartDate,
                    entryProgram.inquiryRecords.trainingEndDate,
                    entryProgram.inputType,
                    entryProgram.groups,
                    user.userNm,
                    entryProgram.createdAt
                ))
                .from(entryProgram)
                .where(builder)
                .join(user).on(entryProgram.createdBy.eq(user.userId))
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<EntryProgram> countQuery = queryFactory
                .selectFrom(entryProgram)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public void deleteByInquiryRecordId(Long inquiryRecordId) {
        queryFactory.delete(entryProgram)
                .where(entryProgram.inquiryRecords.id.eq(inquiryRecordId))
                .execute();
    }

    @Override
    public EntryProgram findByProgramId(Long id) {
        return queryFactory
                .selectFrom(entryProgram)
                .leftJoin(entryProgram.inquiryRecords, inquiryRecords).fetchJoin()
                .leftJoin(inquiryRecords.clients, clients).fetchJoin()
                .where(entryProgram.id.eq(id))
                .fetchFirst();
    }
}
