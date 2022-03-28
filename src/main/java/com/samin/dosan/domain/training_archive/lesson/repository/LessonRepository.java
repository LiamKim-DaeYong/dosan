package com.samin.dosan.domain.training_archive.lesson.repository;

import com.samin.dosan.domain.training_archive.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>, LessonRepositoryQueryDsl {
}
