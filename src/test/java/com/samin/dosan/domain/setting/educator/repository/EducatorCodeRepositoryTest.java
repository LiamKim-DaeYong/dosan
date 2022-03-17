package com.samin.dosan.domain.setting.educator.repository;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.TestConfig;
import com.samin.dosan.domain.setting.educator.EducatorCode;
import com.samin.dosan.domain.setting.educator.EducatorCodeType;
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
class EducatorCodeRepositoryTest {

    @Autowired
    EducatorCodeRepository educatorCodeRepository;

    List<EducatorCode> typeList;
    List<EducatorCode> chargeList;
    List<EducatorCode> belongList;
    List<EducatorCode> branchList;

    EducatorCodeType type = EducatorCodeType.TYPE;
    EducatorCodeType charge = EducatorCodeType.CHARGE;
    EducatorCodeType belong = EducatorCodeType.BELONG;
    EducatorCodeType branch = EducatorCodeType.BRANCH;

    SearchParam searchParam;
    Pageable pageable;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        pageable = PageRequest.of(0, 20);

        typeList = Arrays.asList(
                EducatorCode.builder().code("구분1").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분2").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분3").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분4").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분5").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분6").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분7").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분8").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분9").educatorCodeType(type).used(Used.Y).build(),
                EducatorCode.builder().code("구분10").educatorCodeType(type).used(Used.Y).build()
        );

        educatorCodeRepository.saveAll(typeList);

        chargeList = Arrays.asList(
                EducatorCode.builder().code("담당1").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당2").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당3").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당4").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당5").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당6").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당7").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당8").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당9").educatorCodeType(charge).used(Used.Y).build(),
                EducatorCode.builder().code("담당10").educatorCodeType(charge).used(Used.Y).build()
        );

        educatorCodeRepository.saveAll(chargeList);

        belongList = Arrays.asList(
                EducatorCode.builder().code("소속1").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속2").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속3").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속4").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속5").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속6").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속7").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속8").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속9").educatorCodeType(belong).used(Used.Y).build(),
                EducatorCode.builder().code("소속10").educatorCodeType(belong).used(Used.Y).build()
        );

        educatorCodeRepository.saveAll(belongList);

        branchList = Arrays.asList(
                EducatorCode.builder().code("지부1").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부2").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부3").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부4").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부5").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부6").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부7").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부8").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부9").educatorCodeType(branch).used(Used.Y).build(),
                EducatorCode.builder().code("지부10").educatorCodeType(branch).used(Used.Y).build()
        );

        educatorCodeRepository.saveAll(branchList);
    }

    @Test
    @DisplayName("지도위원 설정 구분 조회 테스트")
    void findType() {
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEducatorCode(typeList.get(0), findList.get(9));
        isEqualToEducatorCode(typeList.get(1), findList.get(8));
        isEqualToEducatorCode(typeList.get(2), findList.get(7));
        isEqualToEducatorCode(typeList.get(3), findList.get(6));
        isEqualToEducatorCode(typeList.get(4), findList.get(5));
        isEqualToEducatorCode(typeList.get(5), findList.get(4));
        isEqualToEducatorCode(typeList.get(6), findList.get(3));
        isEqualToEducatorCode(typeList.get(7), findList.get(2));
        isEqualToEducatorCode(typeList.get(8), findList.get(1));
        isEqualToEducatorCode(typeList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 담당 조회 테스트")
    void findCharge() {
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, charge, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEducatorCode(chargeList.get(0), findList.get(9));
        isEqualToEducatorCode(chargeList.get(1), findList.get(8));
        isEqualToEducatorCode(chargeList.get(2), findList.get(7));
        isEqualToEducatorCode(chargeList.get(3), findList.get(6));
        isEqualToEducatorCode(chargeList.get(4), findList.get(5));
        isEqualToEducatorCode(chargeList.get(5), findList.get(4));
        isEqualToEducatorCode(chargeList.get(6), findList.get(3));
        isEqualToEducatorCode(chargeList.get(7), findList.get(2));
        isEqualToEducatorCode(chargeList.get(8), findList.get(1));
        isEqualToEducatorCode(chargeList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 소속 조회 테스트")
    void findBelong() {
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, belong, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEducatorCode(belongList.get(0), findList.get(9));
        isEqualToEducatorCode(belongList.get(1), findList.get(8));
        isEqualToEducatorCode(belongList.get(2), findList.get(7));
        isEqualToEducatorCode(belongList.get(3), findList.get(6));
        isEqualToEducatorCode(belongList.get(4), findList.get(5));
        isEqualToEducatorCode(belongList.get(5), findList.get(4));
        isEqualToEducatorCode(belongList.get(6), findList.get(3));
        isEqualToEducatorCode(belongList.get(7), findList.get(2));
        isEqualToEducatorCode(belongList.get(8), findList.get(1));
        isEqualToEducatorCode(belongList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 지부 조회 테스트")
    void findBranch() {
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, branch, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEducatorCode(branchList.get(0), findList.get(9));
        isEqualToEducatorCode(branchList.get(1), findList.get(8));
        isEqualToEducatorCode(branchList.get(2), findList.get(7));
        isEqualToEducatorCode(branchList.get(3), findList.get(6));
        isEqualToEducatorCode(branchList.get(4), findList.get(5));
        isEqualToEducatorCode(branchList.get(5), findList.get(4));
        isEqualToEducatorCode(branchList.get(6), findList.get(3));
        isEqualToEducatorCode(branchList.get(7), findList.get(2));
        isEqualToEducatorCode(branchList.get(8), findList.get(1));
        isEqualToEducatorCode(branchList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 검색 테스트")
    void findWorld() {
        searchParam.setSearchWorld("담당");
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("구분1");
        List<EducatorCode> findList2 = educatorCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);

        isEqualToEducatorCode(typeList.get(0), findList2.get(1));
        isEqualToEducatorCode(typeList.get(9), findList2.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 페이징 테스트")
    void pagingEducatorCode() {
        pageable = PageRequest.of(1, 10);
        List<EducatorCode> findList = educatorCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<EducatorCode> findList2 = educatorCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToEducatorCode(typeList.get(0), findList2.get(4));
        isEqualToEducatorCode(typeList.get(1), findList2.get(3));
        isEqualToEducatorCode(typeList.get(2), findList2.get(2));
        isEqualToEducatorCode(typeList.get(3), findList2.get(1));
        isEqualToEducatorCode(typeList.get(4), findList2.get(0));
    }

    private void isEqualToEducatorCode(EducatorCode educatorCode, EducatorCode findEducatorCode) {
        assertThat(findEducatorCode.getCode()).isEqualTo(educatorCode.getCode());
        assertThat(findEducatorCode.getEducatorCodeType()).isEqualTo(educatorCode.getEducatorCodeType());
        assertThat(findEducatorCode.getUsed()).isEqualTo(educatorCode.getUsed());
    }
}
