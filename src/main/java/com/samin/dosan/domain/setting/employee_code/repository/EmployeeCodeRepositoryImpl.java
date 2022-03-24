package com.samin.dosan.domain.setting.employee_code.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.employee_code.EmployeeCode;
import com.samin.dosan.domain.setting.employee_code.EmployeeCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.employee_code.QEmployeeCode.employeeCode;

@RequiredArgsConstructor
public class EmployeeCodeRepositoryImpl implements EmployeeCodeRepositoryQueryDsl {

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
    public List<EmployeeCode> findAll(EmployeeCodeType employeeCodeType) {
        return queryFactory
                .selectFrom(employeeCode)
                .where(employeeCode.employeeCodeType.eq(employeeCodeType)
                        .and(employeeCode.used.eq(Used.Y)))
                .fetch();
    }
}
