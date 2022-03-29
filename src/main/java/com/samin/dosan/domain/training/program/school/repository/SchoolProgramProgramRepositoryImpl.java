package com.samin.dosan.domain.training.program.school.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training.program.file.QProgramFile;
import com.samin.dosan.domain.training.program.school.QSchoolProgram;
import com.samin.dosan.domain.training.program.school.SchoolProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.training.program.file.QProgramFile.programFile;
import static com.samin.dosan.domain.training.program.school.QSchoolProgram.schoolProgram;

@RequiredArgsConstructor
public class SchoolProgramProgramRepositoryImpl implements SchoolProgramRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SchoolProgram> findAll(SearchParam searchParam, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        List<SchoolProgram> content = queryFactory
                .selectFrom(schoolProgram)
                .where(builder)
                .leftJoin(schoolProgram.assignmentTable, programFile).fetchJoin()
                .leftJoin(schoolProgram.program, programFile).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<SchoolProgram> countQuery = queryFactory
                .selectFrom(schoolProgram)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
