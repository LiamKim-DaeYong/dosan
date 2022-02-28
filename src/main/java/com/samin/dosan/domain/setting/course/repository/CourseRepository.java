package com.samin.dosan.domain.setting.course.repository;

import com.samin.dosan.domain.setting.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryQueryDsl {
}
