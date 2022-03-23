package com.samin.dosan.domain.user.employees.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.user.employees.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.user.entity.QEmployee.employee;
import static com.samin.dosan.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Employee> findAll(SearchParam searchParam, Long employeeCodeId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(employee.user.used.eq(Used.Y));

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
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Employee> countQuery = queryFactory
                .selectFrom(employee)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }

    @Override
    public Employee findByUserId(String userId) {
        return queryFactory
                .selectFrom(employee)
                .leftJoin(employee.user, user).fetchJoin()
                .where(employee.user.userId.eq(userId))
                .fetchOne();
    }
}
