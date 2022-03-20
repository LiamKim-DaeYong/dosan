package com.samin.dosan.domain.setting.employees.repository;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.TestConfig;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import com.samin.dosan.domain.setting.employee.repository.EmployeeCodeRepository;
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
class EmployeesCodeRepositoryTest {

    @Autowired
    EmployeeCodeRepository employeesCodeRepository;

    List<EmployeeCode> typeList;
    List<EmployeeCode> positionList;
    List<EmployeeCode> rankList;
    List<EmployeeCode> stepList;
    List<EmployeeCode> departmentList;

    EmployeeCodeType type = EmployeeCodeType.TYPE;
    EmployeeCodeType position = EmployeeCodeType.POSITION;
    EmployeeCodeType rank = EmployeeCodeType.RANK;
    EmployeeCodeType step = EmployeeCodeType.STEP;
    EmployeeCodeType department = EmployeeCodeType.DEPARTMENT;

    SearchParam searchParam;
    Pageable pageable;

    @BeforeEach
    void init() {
        searchParam = new SearchParam();
        pageable = PageRequest.of(0, 20);

        typeList = Arrays.asList(
                EmployeeCode.builder().code("구분1").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분2").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분3").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분4").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분5").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분6").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분7").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분8").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분9").employeeCodeType(type).used(Used.Y).build(),
                EmployeeCode.builder().code("구분10").employeeCodeType(type).used(Used.Y).build()
        );

        employeesCodeRepository.saveAll(typeList);

        positionList = Arrays.asList(
                EmployeeCode.builder().code("직위1").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위2").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위3").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위4").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위5").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위6").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위7").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위8").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위9").employeeCodeType(position).used(Used.Y).build(),
                EmployeeCode.builder().code("직위10").employeeCodeType(position).used(Used.Y).build()
        );

        employeesCodeRepository.saveAll(positionList);

        rankList = Arrays.asList(
                EmployeeCode.builder().code("직급1").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급2").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급3").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급4").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급5").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급6").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급7").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급8").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급9").employeeCodeType(rank).used(Used.Y).build(),
                EmployeeCode.builder().code("직급10").employeeCodeType(rank).used(Used.Y).build()
        );

        employeesCodeRepository.saveAll(rankList);

        stepList = Arrays.asList(
                EmployeeCode.builder().code("호봉1").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉2").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉3").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉4").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉5").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉6").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉7").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉8").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉9").employeeCodeType(step).used(Used.Y).build(),
                EmployeeCode.builder().code("호봉10").employeeCodeType(step).used(Used.Y).build()
        );

        employeesCodeRepository.saveAll(stepList);

        departmentList = Arrays.asList(
                EmployeeCode.builder().code("근무부서1").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서2").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서3").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서4").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서5").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서6").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서7").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서8").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서9").employeeCodeType(department).used(Used.Y).build(),
                EmployeeCode.builder().code("근무부서10").employeeCodeType(department).used(Used.Y).build()
        );

        employeesCodeRepository.saveAll(departmentList);
    }

    @Test
    @DisplayName("임직원 설정 구분 조회 테스트")
    void findType() {
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEmployeesCode(typeList.get(0), findList.get(9));
        isEqualToEmployeesCode(typeList.get(1), findList.get(8));
        isEqualToEmployeesCode(typeList.get(2), findList.get(7));
        isEqualToEmployeesCode(typeList.get(3), findList.get(6));
        isEqualToEmployeesCode(typeList.get(4), findList.get(5));
        isEqualToEmployeesCode(typeList.get(5), findList.get(4));
        isEqualToEmployeesCode(typeList.get(6), findList.get(3));
        isEqualToEmployeesCode(typeList.get(7), findList.get(2));
        isEqualToEmployeesCode(typeList.get(8), findList.get(1));
        isEqualToEmployeesCode(typeList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("임직원 설정 직위 조회 테스트")
    void findPosition() {
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, position, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEmployeesCode(positionList.get(0), findList.get(9));
        isEqualToEmployeesCode(positionList.get(1), findList.get(8));
        isEqualToEmployeesCode(positionList.get(2), findList.get(7));
        isEqualToEmployeesCode(positionList.get(3), findList.get(6));
        isEqualToEmployeesCode(positionList.get(4), findList.get(5));
        isEqualToEmployeesCode(positionList.get(5), findList.get(4));
        isEqualToEmployeesCode(positionList.get(6), findList.get(3));
        isEqualToEmployeesCode(positionList.get(7), findList.get(2));
        isEqualToEmployeesCode(positionList.get(8), findList.get(1));
        isEqualToEmployeesCode(positionList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("임직원 설정 직급 조회 테스트")
    void findRank() {
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, rank, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEmployeesCode(rankList.get(0), findList.get(9));
        isEqualToEmployeesCode(rankList.get(1), findList.get(8));
        isEqualToEmployeesCode(rankList.get(2), findList.get(7));
        isEqualToEmployeesCode(rankList.get(3), findList.get(6));
        isEqualToEmployeesCode(rankList.get(4), findList.get(5));
        isEqualToEmployeesCode(rankList.get(5), findList.get(4));
        isEqualToEmployeesCode(rankList.get(6), findList.get(3));
        isEqualToEmployeesCode(rankList.get(7), findList.get(2));
        isEqualToEmployeesCode(rankList.get(8), findList.get(1));
        isEqualToEmployeesCode(rankList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("임직원 설정 호봉 조회 테스트")
    void findStep() {
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, step, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEmployeesCode(stepList.get(0), findList.get(9));
        isEqualToEmployeesCode(stepList.get(1), findList.get(8));
        isEqualToEmployeesCode(stepList.get(2), findList.get(7));
        isEqualToEmployeesCode(stepList.get(3), findList.get(6));
        isEqualToEmployeesCode(stepList.get(4), findList.get(5));
        isEqualToEmployeesCode(stepList.get(5), findList.get(4));
        isEqualToEmployeesCode(stepList.get(6), findList.get(3));
        isEqualToEmployeesCode(stepList.get(7), findList.get(2));
        isEqualToEmployeesCode(stepList.get(8), findList.get(1));
        isEqualToEmployeesCode(stepList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("임직원 설정 근무부서 조회 테스트")
    void findDepartment() {
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, department, pageable).getContent();
        assertThat(findList.size()).isEqualTo(10);

        isEqualToEmployeesCode(departmentList.get(0), findList.get(9));
        isEqualToEmployeesCode(departmentList.get(1), findList.get(8));
        isEqualToEmployeesCode(departmentList.get(2), findList.get(7));
        isEqualToEmployeesCode(departmentList.get(3), findList.get(6));
        isEqualToEmployeesCode(departmentList.get(4), findList.get(5));
        isEqualToEmployeesCode(departmentList.get(5), findList.get(4));
        isEqualToEmployeesCode(departmentList.get(6), findList.get(3));
        isEqualToEmployeesCode(departmentList.get(7), findList.get(2));
        isEqualToEmployeesCode(departmentList.get(8), findList.get(1));
        isEqualToEmployeesCode(departmentList.get(9), findList.get(0));
    }

    @Test
    @DisplayName("임직원 설정 검색 테스트")
    void findWorld() {
        searchParam.setSearchWorld("직위");
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        searchParam.setSearchWorld("구분1");
        List<EmployeeCode> findList2 = employeesCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(2);

        isEqualToEmployeesCode(typeList.get(0), findList2.get(1));
        isEqualToEmployeesCode(typeList.get(9), findList2.get(0));
    }

    @Test
    @DisplayName("지도위원 설정 페이징 테스트")
    void pagingEducatorCode() {
        pageable = PageRequest.of(1, 10);
        List<EmployeeCode> findList = employeesCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList.size()).isEqualTo(0);

        pageable = PageRequest.of(1, 5);
        List<EmployeeCode> findList2 = employeesCodeRepository.findAll(searchParam, type, pageable).getContent();
        assertThat(findList2.size()).isEqualTo(5);

        isEqualToEmployeesCode(typeList.get(0), findList2.get(4));
        isEqualToEmployeesCode(typeList.get(1), findList2.get(3));
        isEqualToEmployeesCode(typeList.get(2), findList2.get(2));
        isEqualToEmployeesCode(typeList.get(3), findList2.get(1));
        isEqualToEmployeesCode(typeList.get(4), findList2.get(0));
    }

    private void isEqualToEmployeesCode(EmployeeCode employeesCode, EmployeeCode findEmployeesCode) {
        assertThat(findEmployeesCode.getCode()).isEqualTo(employeesCode.getCode());
        assertThat(findEmployeesCode.getEmployeeCodeType()).isEqualTo(employeesCode.getEmployeeCodeType());
        assertThat(findEmployeesCode.getUsed()).isEqualTo(employeesCode.getUsed());
    }

}
