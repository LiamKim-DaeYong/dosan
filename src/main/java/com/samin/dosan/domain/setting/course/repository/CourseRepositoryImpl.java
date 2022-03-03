package com.samin.dosan.domain.setting.course.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.setting.course.QCourse.course;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Course> findAll(SearchParam searchParam, CourseType courseType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(course.courseType.eq(courseType).and(course.used.eq(Used.Y)));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(course.subject.contains(searchWorld)
                    .or(course.content.contains(searchWorld)));
        }

        List<Course> content = queryFactory
                .selectFrom(course)
                .where(builder)
                .orderBy(course.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Course> countQuery = queryFactory
                .selectFrom(course)
                .where(builder)
                .orderBy(course.id.desc());

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
