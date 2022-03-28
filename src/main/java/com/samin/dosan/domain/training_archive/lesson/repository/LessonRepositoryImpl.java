package com.samin.dosan.domain.training_archive.lesson.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.training_archive.lesson.GradeType;
import com.samin.dosan.domain.training_archive.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.samin.dosan.domain.training_archive.lesson.QLesson.lesson;

@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Lesson> findAll(SearchParam searchParam, Pageable pageable, String gradeType) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(lesson.used.eq(Used.Y)
                .and(lesson.gradeType.eq(GradeType.valueOf(StrUtils.urlToEnumName(gradeType)))));

        String searchWorld = searchParam.getSearchWorld();
        if (searchWorld != null) {
            builder.and(lesson.title.contains(searchWorld));
        }

        List<Lesson> content = queryFactory
                .selectFrom(lesson)
                .where(builder)
                .orderBy(lesson.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Lesson> countQuery = queryFactory
                .selectFrom(lesson)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery.fetch()::size);
    }
}
