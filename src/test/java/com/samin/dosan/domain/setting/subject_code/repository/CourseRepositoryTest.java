package com.samin.dosan.domain.setting.subject_code.repository;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.TestConfig;
import com.samin.dosan.domain.setting.subject_code.Course;
import com.samin.dosan.domain.setting.subject_code.CourseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestConfig.class)
class CourseRepositoryTest {

    @Autowired CourseRepository courseRepository;

    CourseType entry = CourseType.ENTRY;
    CourseType explr = CourseType.EXPLR;

    List<Course> entryList;
    List<Course> explrList;
    SearchParam searchParam;
    Pageable pageable;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        pageable = PageRequest.of(0, 20);

        entryList = Arrays.asList(
                Course.builder().subject("입교1").content("입교 내용1").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교2").content("입교 내용2").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교3").content("입교 내용3").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교4").content("입교 내용4").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교5").content("입교 내용5").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교6").content("입교 내용6").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교7").content("입교 내용7").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교8").content("입교 내용8").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교9").content("입교 내용9").courseType(entry).used(Used.Y).build(),
                Course.builder().subject("입교10").content("입교 내용10").courseType(entry).used(Used.Y).build()
        );

        courseRepository.saveAll(entryList);

        explrList = Arrays.asList(
                Course.builder().subject("탐방지1").content("탐방지 내용1").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지2").content("탐방지 내용2").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지3").content("탐방지 내용3").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지4").content("탐방지 내용4").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지5").content("탐방지 내용5").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지6").content("탐방지 내용6").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지7").content("탐방지 내용7").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지8").content("탐방지 내용8").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지9").content("탐방지 내용9").courseType(explr).used(Used.Y).build(),
                Course.builder().subject("탐방지10").content("탐방지 내용10").courseType(explr).used(Used.Y).build()
        );

        courseRepository.saveAll(explrList);
    }

    @Test
    @DisplayName("입교 조회 테스트")
    void findAllEntry() {
        List<Course> findList = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToCourse(entryList.get(0), findList.get(9));
        isEqualToCourse(entryList.get(1), findList.get(8));
        isEqualToCourse(entryList.get(2), findList.get(7));
        isEqualToCourse(entryList.get(3), findList.get(6));
        isEqualToCourse(entryList.get(4), findList.get(5));
        isEqualToCourse(entryList.get(5), findList.get(4));
        isEqualToCourse(entryList.get(6), findList.get(3));
        isEqualToCourse(entryList.get(7), findList.get(2));
        isEqualToCourse(entryList.get(8), findList.get(1));
        isEqualToCourse(entryList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("입교 검색 테스트")
    void searchEntry() {
        searchParam.setSearchWorld("탐방지");
        List<Course> findList = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("입교1");
        List<Course> findList2 = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);
        isEqualToCourse(entryList.get(0), findList2.get(1));
        isEqualToCourse(entryList.get(9), findList2.get(0));

        searchParam.setSearchWorld("내용4");
        List<Course> findList3 = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList3.size()).isEqualTo(1);
        isEqualToCourse(entryList.get(3), findList3.get(0));
    }

    @Test
    @DisplayName("입교 페이징 테스트")
    void pagingEntry() {
        pageable = PageRequest.of(1, 10);
        List<Course> findList = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<Course> findList2 = courseRepository.findAll(searchParam, entry, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToCourse(entryList.get(0), findList2.get(4));
        isEqualToCourse(entryList.get(1), findList2.get(3));
        isEqualToCourse(entryList.get(2), findList2.get(2));
        isEqualToCourse(entryList.get(3), findList2.get(1));
        isEqualToCourse(entryList.get(4), findList2.get(0));
    }

    @Test
    @DisplayName("탐방지 조회 테스트")
    void findAllExplr() {
        List<Course> findList = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToCourse(explrList.get(0), findList.get(9));
        isEqualToCourse(explrList.get(1), findList.get(8));
        isEqualToCourse(explrList.get(2), findList.get(7));
        isEqualToCourse(explrList.get(3), findList.get(6));
        isEqualToCourse(explrList.get(4), findList.get(5));
        isEqualToCourse(explrList.get(5), findList.get(4));
        isEqualToCourse(explrList.get(6), findList.get(3));
        isEqualToCourse(explrList.get(7), findList.get(2));
        isEqualToCourse(explrList.get(8), findList.get(1));
        isEqualToCourse(explrList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("탐방지 검색 테스트")
    void searchExplr() {
        searchParam.setSearchWorld("입교");
        List<Course> findList = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("탐방지1");
        List<Course> findList2 = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);
        isEqualToCourse(explrList.get(0), findList2.get(1));
        isEqualToCourse(explrList.get(9), findList2.get(0));

        searchParam.setSearchWorld("내용4");
        List<Course> findList3 = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList3.size()).isEqualTo(1);
        isEqualToCourse(explrList.get(3), findList3.get(0));
    }

    @Test
    @DisplayName("탐방지 페이징 테스트")
    void pagingExplr() {
        pageable = PageRequest.of(1, 10);
        List<Course> findList = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<Course> findList2 = courseRepository.findAll(searchParam, explr, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToCourse(explrList.get(0), findList2.get(4));
        isEqualToCourse(explrList.get(1), findList2.get(3));
        isEqualToCourse(explrList.get(2), findList2.get(2));
        isEqualToCourse(explrList.get(3), findList2.get(1));
        isEqualToCourse(explrList.get(4), findList2.get(0));
    }

    private void isEqualToCourse(Course course, Course findCourse) {
        assertThat(findCourse.getSubject()).isEqualTo(course.getSubject());
        assertThat(findCourse.getContent()).isEqualTo(course.getContent());
        assertThat(findCourse.getCourseType()).isEqualTo(course.getCourseType());
        assertThat(findCourse.getUsed()).isEqualTo(course.getUsed());
    }
}
