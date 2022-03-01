package com.samin.dosan.domain.setting.course.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.type.setting.CourseType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samin.dosan.domain.setting.course.QCourse.course;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Course> findAll(SearchParam searchParam, CourseType courseType) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(course.courseType.eq(courseType));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(course.subject.contains(searchWorld))
                    .or(course.content.contains(searchWorld));
        }

        return queryFactory.selectFrom(course)
                .where(builder)
                .orderBy(course.id.desc())
                .fetch();
    }

    @Override
    public boolean existsBySubject(String subject, CourseType courseType) {
        return queryFactory.selectFrom(course)
                .where(course.subject.eq(subject).and(course.courseType.eq(courseType)))
                .fetchFirst() != null;
    }
}
