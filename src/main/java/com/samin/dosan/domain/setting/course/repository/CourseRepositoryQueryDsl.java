package com.samin.dosan.domain.setting.course.repository;

import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.type.CourseType;
import com.samin.dosan.web.param.SearchParam;

import java.util.List;

public interface CourseRepositoryQueryDsl {
    List<Course> findAll(SearchParam searchParam, CourseType courseType);
}
