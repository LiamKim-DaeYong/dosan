package com.samin.dosan.domain.training_archive.lesson;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.training_archive.lesson.repository.LessonRepository;
import com.samin.dosan.web.dto.training_archive.LessonSave;
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
public class LessonService {

    private final LessonRepository lessonRepository;

    public Page<Lesson> findAll(SearchParam searchParam, Pageable pageable, String gradeType) {
        return lessonRepository.findAll(searchParam, pageable, gradeType);
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long save(Lesson saveData) {
        return lessonRepository.save(saveData).getId();
    }

    @Transactional
    public Long update(Long id, LessonSave updateData) {
        findById(id).update(updateData);
        return id;
    }

    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(id -> findById(id).delete());
    }
}
