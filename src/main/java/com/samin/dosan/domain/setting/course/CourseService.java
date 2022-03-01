package com.samin.dosan.domain.setting.course;

import com.samin.dosan.domain.setting.course.repository.CourseRepository;
import com.samin.dosan.domain.type.setting.CourseType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(SearchParam searchParam, CourseType courseType) {
        return courseRepository.findAll(searchParam, courseType);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void update(Course updateData) {
        Course findCourse = findById(updateData.getId());
        findCourse.update(updateData);
    }
}
