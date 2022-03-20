package com.samin.dosan.domain.setting.former.repository;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.TestConfig;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.repository.FormerJobCodeRepository;
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
class FormerJobCodeRepositoryTest {

    @Autowired
    FormerJobCodeRepository formerJobCodeRepository;

    List<FormerJobCode> formerList;

    SearchParam searchParam;
    Pageable pageable;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        pageable = PageRequest.of(0, 20);

        formerList = Arrays.asList(
                FormerJobCode.builder().formerNm("전직1").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직2").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직3").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직4").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직5").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직6").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직7").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직8").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직9").used(Used.Y).build(),
                FormerJobCode.builder().formerNm("전직10").used(Used.Y).build()
        );

        formerJobCodeRepository.saveAll(formerList);
    }

    @Test
    @DisplayName("전직 구분 조회 테스트")
    void findAll() {
        List<FormerJobCode> findList = formerJobCodeRepository.findAll(searchParam, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualFormer(formerList.get(0), findList.get(9));
        isEqualFormer(formerList.get(1), findList.get(8));
        isEqualFormer(formerList.get(2), findList.get(7));
        isEqualFormer(formerList.get(3), findList.get(6));
        isEqualFormer(formerList.get(4), findList.get(5));
        isEqualFormer(formerList.get(5), findList.get(4));
        isEqualFormer(formerList.get(6), findList.get(3));
        isEqualFormer(formerList.get(7), findList.get(2));
        isEqualFormer(formerList.get(8), findList.get(1));
        isEqualFormer(formerList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("전직 구분 검색 테스트")
    void findWorld() {
        searchParam.setSearchWorld("초등교원");
        List<FormerJobCode> findList = formerJobCodeRepository.findAll(searchParam, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("전직1");
        List<FormerJobCode> findList2 = formerJobCodeRepository.findAll(searchParam, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);

        isEqualFormer(formerList.get(0), findList2.get(1));
        isEqualFormer(formerList.get(9), findList2.get(0));
    }

    @Test
    @DisplayName("전직 구분 페이징 테스트")
    void pagingFormer() {
        pageable = PageRequest.of(1, 10);
        List<FormerJobCode> findList = formerJobCodeRepository.findAll(searchParam, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<FormerJobCode> findList2 = formerJobCodeRepository.findAll(searchParam, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualFormer(formerList.get(0), findList2.get(4));
        isEqualFormer(formerList.get(1), findList2.get(3));
        isEqualFormer(formerList.get(2), findList2.get(2));
        isEqualFormer(formerList.get(3), findList2.get(1));
        isEqualFormer(formerList.get(4), findList2.get(0));
    }

    private void isEqualFormer(FormerJobCode former, FormerJobCode findFormer) {
        assertThat(findFormer.getFormerNm()).isEqualTo(former.getFormerNm());
        assertThat(findFormer.getUsed()).isEqualTo(former.getUsed());
    }

}
