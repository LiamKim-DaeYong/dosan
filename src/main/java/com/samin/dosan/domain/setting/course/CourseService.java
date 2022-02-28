package com.samin.dosan.domain.setting.course;

import com.samin.dosan.domain.setting.course.repository.CourseRepository;
import com.samin.dosan.domain.type.CourseType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(SearchParam searchParam, CourseType courseType) {
        return courseRepository.findAll(searchParam, courseType);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }
}
