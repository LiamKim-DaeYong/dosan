package com.samin.dosan.domain.setting.employee.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import com.samin.dosan.domain.setting.employee.QEmployeeCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.employee.QEmployeeCode.employeeCode;

@RequiredArgsConstructor
public class EmployeesCodeRepositoryImpl implements EmployeesCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EmployeeCode> findAll(SearchParam searchParam, EmployeeCodeType employeeCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(employeeCode.employeeCodeType.eq(employeeCodeType).and(employeeCode.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(employeeCode.code.contains(searchWorld));
        }

        List<EmployeeCode> content = queryFactory
                .selectFrom(employeeCode)
                .where(builder)
                .orderBy(employeeCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<EmployeeCode> countQUery = queryFactory
                .selectFrom(employeeCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQUery.fetch()::size);
    }

    @Override
    public List<EmployeeCode> findAllTypes() {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(EmployeeCodeType.TYPE)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllPosition() {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(EmployeeCodeType.POSITION)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllRank() {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(EmployeeCodeType.RANK)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllStep() {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(EmployeeCodeType.STEP)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllDepartment() {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(EmployeeCodeType.DEPARTMENT)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }
}
