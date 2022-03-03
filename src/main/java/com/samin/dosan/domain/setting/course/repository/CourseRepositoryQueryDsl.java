package com.samin.dosan.domain.setting.course.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.Course;
import com.samin.dosan.domain.setting.course.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepositoryQueryDsl {
    Page<Course> findAll(SearchParam searchParam, CourseType courseType, Pageable pageable);
}
