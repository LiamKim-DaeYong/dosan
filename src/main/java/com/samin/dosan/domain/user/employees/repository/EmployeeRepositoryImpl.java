package com.samin.dosan.domain.user.employees.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.employee_code.QEmployeeCode.employeeCode;
import static com.samin.dosan.domain.user.employees.entity.QEmployee.employee;
import static com.samin.dosan.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(employee.user.used.eq(Used.Y));

        if (employeeCodeId != null) {
            builder.and(employee.employeeType.id.eq(employeeCodeId));
        }

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(employee.user.userNm.contains(searchWorld)

                    .or(employee.employeeType.code.contains(searchWorld))
                    .or(employee.employeePosition.code.contains(searchWorld))
                    .or(employee.employeeRank.code.contains(searchWorld))
                    .or(employee.employeeStep.code.contains(searchWorld))
                    .or(employee.employeeDepartment.code.contains(searchWorld))

                    .or(employee.phoneNum.contains(searchWorld))
                    .or(employee.cmpAddress.roadAddress.contains(searchWorld))
            );
        }

        List<Employee> content = queryFactory
                .selectFrom(employee)
                .leftJoin(employee.user, user).fetchJoin()
                .leftJoin(employee.employeeType, employeeCode).fetchJoin()
                .leftJoin(employee.employeePosition, employeeCode).fetchJoin()
                .leftJoin(employee.employeeRank, employeeCode).fetchJoin()
                .leftJoin(employee.employeeStep, employeeCode).fetchJoin()
                .leftJoin(employee.employeeDepartment, employeeCode).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Employee> countQuery = queryFactory
                .selectFrom(employee)
                .leftJoin(employee.user, user).fetchJoin()
                .leftJoin(employee.employeeType, employeeCode).fetchJoin()
                .leftJoin(employee.employeePosition, employeeCode).fetchJoin()
                .leftJoin(employee.employeeRank, employeeCode).fetchJoin()
                .leftJoin(employee.employeeStep, employeeCode).fetchJoin()
                .leftJoin(employee.employeeDepartment, employeeCode).fetchJoin()
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public Employee findByUserId(String userId) {
        return queryFactory
                .selectFrom(employee)
                .leftJoin(employee.user, user).fetchJoin()
                .leftJoin(employee.employeeType, employeeCode).fetchJoin()
                .leftJoin(employee.employeePosition, employeeCode).fetchJoin()
                .leftJoin(employee.employeeRank, employeeCode).fetchJoin()
                .leftJoin(employee.employeeStep, employeeCode).fetchJoin()
                .leftJoin(employee.employeeDepartment, employeeCode).fetchJoin()
                .where(employee.user.userId.eq(userId))
                .fetchOne();
    }
}
