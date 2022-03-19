package com.samin.dosan.domain.setting.employee.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.employee.QEmployeesCode.employeesCode;

@RequiredArgsConstructor
public class EmployeesCodeRepositoryImpl implements EmployeesCodeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EmployeeCode> findAll(SearchParam searchParam, EmployeeCodeType employeeCodeType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(employeesCode.employeesCodeType.eq(employeeCodeType).and(employeesCode.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(employeesCode.code.contains(searchWorld));
        }

        List<EmployeeCode> content = queryFactory
                .selectFrom(employeesCode)
                .where(builder)
                .orderBy(employeesCode.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<EmployeeCode> countQUery = queryFactory
                .selectFrom(employeesCode)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQUery.fetch()::size);
    }

    @Override
    public List<EmployeeCode> findAllTypes() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeeCodeType.TYPE)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllPosition() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeeCodeType.POSITION)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllRank() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeeCodeType.RANK)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllStep() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeeCodeType.STEP)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }

    @Override
    public List<EmployeeCode> findAllDepartment() {
        return queryFactory
                .selectFrom(employeesCode)
                .where(employeesCode.employeesCodeType.eq(EmployeeCodeType.DEPARTMENT)
                        .and(employeesCode.used.eq(Used.Y)))
                .fetch();
    }
}
