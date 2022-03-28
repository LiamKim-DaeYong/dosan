package com.samin.dosan.domain.training_archive.lesson.repository;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.lesson.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonRepositoryQueryDsl {

    Page<Lesson> findAll(SearchParam searchParam, Pageable pageable, String gradeType);
}
