package com.samin.dosan.domain.setting.employees.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employees.EmployeesCode;
import com.samin.dosan.domain.setting.employees.EmployeesCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.employees.QEmployeesCode.employeesCode;

@RequiredArgsConstructor
public class EmployeesCodeRepositoryImpl implements EmployeesCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EmployeesCode> findAll(SearchParam searchParam, EmployeesCodeType employeesCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(employeesCode.employeesCodeType.eq(employeesCodeType).and(employeesCode.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(employeesCode.code.contains(searchWorld));
        }

        List<EmployeesCode> content = queryFactory
                .selectFrom(employeesCode)
                .where(builder)
                .orderBy(employeesCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<EmployeesCode> countQUery = queryFactory
                .selectFrom(employeesCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQUery.fetch()::size);
    }

    @Override
    public List<EmployeesCode> findAllTypes() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeesCodeType.TYPE)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeesCode> findAllPosition() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeesCodeType.POSITION)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeesCode> findAllRank() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeesCodeType.RANK)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeesCode> findAllStep() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeesCodeType.STEP)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeesCode> findAllDepartment() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeesCodeType.DEPARTMENT)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }
}
