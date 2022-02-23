package com.samin.dosan.setting.curriculum;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import com.samin.dosan.domain.setting.curriculum.CurriculumRepository;
import com.samin.dosan.domain.setting.curriculum.CurriculumService;
import com.samin.dosan.domain.type.CurriculumType;
import com.samin.dosan.web.dto.SearchParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurriculumServiceTest {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CurriculumService curriculumService;

    private List<Curriculum> curriculums = Arrays.asList(
            Curriculum.builder().subject("강의").content("선비정신과 퇴계선생").curriculumType(CurriculumType.ENTRY).build(),
            Curriculum.builder().subject("탁본체험").content("퇴계선생의 좌우명 배우기").curriculumType(CurriculumType.ENTRY).build(),
            Curriculum.builder().subject("영상시청").content("애국지사 김용환의 삶").curriculumType(CurriculumType.ENTRY).build()
    );

    private List<Curriculum> curriculums2 = Arrays.asList(
            Curriculum.builder().subject("강의").content("선비정신과 퇴계선생").curriculumType(CurriculumType.ENTRY).build(),
            Curriculum.builder().subject("탁본체험").content("퇴계선생의 좌우명 배우기").curriculumType(CurriculumType.EXPLOR).build(),
            Curriculum.builder().subject("영상시청").content("애국지사 김용환의 삶").curriculumType(CurriculumType.SCHOOL).build()
    );

    @BeforeEach
    public void init() {
        curriculumRepository.deleteAll();
    }

    @Test
    @DisplayName("설정-교육 과목 조회 테스트")
    void findAll() {
        String type = "entry";
        SearchParam searchParam = new SearchParam();

        // 조회 결과 없음
        List<Curriculum> findCurriculums = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums.size()).isEqualTo(0);

        // 조회결과 1
        curriculumService.save(curriculums.get(0));
        List<Curriculum> findCurriculums1 = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums1.size()).isEqualTo(1);
        isEqualToCurriculum(curriculums.get(0), findCurriculums1.get(0));

        // 조회결과 2
        curriculumService.save(curriculums.get(1));
        List<Curriculum> findCurriculums2 = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums2.size()).isEqualTo(2);
        isEqualToCurriculum(curriculums.get(0), findCurriculums2.get(0));
        isEqualToCurriculum(curriculums.get(1), findCurriculums2.get(1));

        // 조회결과 3
        curriculumService.save(curriculums.get(2));
        List<Curriculum> findCurriculums3 = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums3.size()).isEqualTo(3);
        isEqualToCurriculum(curriculums.get(0), findCurriculums3.get(0));
        isEqualToCurriculum(curriculums.get(1), findCurriculums3.get(1));
        isEqualToCurriculum(curriculums.get(2), findCurriculums3.get(2));
    }

    @Test
    @DisplayName("설정-교육 과목 검색 조회 테스트")
    void findAllSearchTest() {
        String type = "school";
        SearchParam searchParam = new SearchParam();
        searchParam.setSearchWorld("강의");

        for (Curriculum curriculum : curriculums2) {
            curriculumService.save(curriculum);
        }

        List<Curriculum> findCurriculums = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums.size()).isEqualTo(0);

        searchParam.setSearchWorld("영상시청");
        List<Curriculum> findCurriculums2 = curriculumService.findAll(type, searchParam);
        assertThat(findCurriculums2.size()).isEqualTo(1);
        isEqualToCurriculum(curriculums2.get(2), findCurriculums2.get(0));
    }

    private void isEqualToCurriculum(Curriculum curriculum1, Curriculum curriculum2) {
        assertThat(curriculum1.getId()).isEqualTo(curriculum2.getId());
        assertThat(curriculum1.getSubject()).isEqualTo(curriculum2.getSubject());
        assertThat(curriculum1.getContent()).isEqualTo(curriculum2.getContent());
    }
}
