package com.samin.dosan.domain.setting.course;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.setting.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> findAll(SearchParam searchParam, CourseType courseType, Pageable pageable) {
        return courseRepository.findAll(searchParam, courseType, pageable);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Course course) {
        return courseRepository.save(course).getId();
    }

    @Transactional
    public Long update(Long id, Course updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
