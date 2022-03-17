package com.samin.dosan.domain.setting.course;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    CourseRepository courseRepository;
    CourseService courseService;

    Course entryCourse;
    Course explrCourse;

    @BeforeEach
    void init() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseService(courseRepository);

        entryCourse = Course.builder()
                .id(1L)
                .subject("입교")
                .content("입교 내용")
                .courseType(CourseType.ENTRY)
                .used(Used.Y)
                .build();

        explrCourse = Course.builder()
                .id(2L)
                .subject("탐방지1")
                .content("탐방지 내용1")
                .courseType(CourseType.EXPLR)
                .used(Used.Y)
                .build();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(entryCourse));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(explrCourse));
        when(courseRepository.findById(3L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        Course findEntry = courseService.findById(1L);
        isEqualToCourse(entryCourse, findEntry);

        Course findExplr = courseService.findById(2L);
        isEqualToCourse(explrCourse, findExplr);

        assertThrows(EntityNotFoundException.class, () -> courseService.findById(3L));
        verify(courseRepository, times(3)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        Course updateData = Course.builder()
                .subject("입교 수정")
                .content("입교 내용 수정")
                .courseType(CourseType.ENTRY)
                .used(Used.Y)
                .build();

        Long updateId = courseService.update(1L, updateData);
        verify(courseRepository, times(1)).findById(anyLong());

        Course update = courseService.findById(updateId);
        isEqualToCourse(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        courseService.delete(ids);
        verify(courseRepository, times(2)).findById(anyLong());

        Course delete1 = courseService.findById(1L);
        Course delete2 = courseService.findById(2L);

        assertThat(delete1.getUsed()).isEqualTo(Used.N);
        assertThat(delete2.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualToCourse(Course course, Course findCourse) {
        assertThat(findCourse.getSubject()).isEqualTo(course.getSubject());
        assertThat(findCourse.getContent()).isEqualTo(course.getContent());
        assertThat(findCourse.getCourseType()).isEqualTo(course.getCourseType());
        assertThat(findCourse.getUsed()).isEqualTo(course.getUsed());
    }
}
