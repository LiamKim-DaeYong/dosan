package com.samin.dosan.domain.setting.course.repository;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.type.setting.CourseType;
import com.samin.dosan.web.param.SearchParam;

import java.util.List;

public interface CourseRepositoryQueryDsl {
    List<Course> findAll(SearchParam searchParam, CourseType courseType);

    boolean existsBySubject(String subject, CourseType courseType);
}
