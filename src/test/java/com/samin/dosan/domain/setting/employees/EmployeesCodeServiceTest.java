package com.samin.dosan.domain.setting.employees;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.employee.EmployeeCode;
import com.samin.dosan.domain.setting.employee.EmployeeCodeService;
import com.samin.dosan.domain.setting.employee.EmployeeCodeType;
import com.samin.dosan.domain.setting.employee.repository.EmployeeCodeRepository;
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

class EmployeesCodeServiceTest {

    EmployeeCodeRepository employeesCodeRepository;
    EmployeeCodeService employeesCodeService;

    EmployeeCode typeEmployeesCode;
    EmployeeCode positionEmployeesCode;
    EmployeeCode rankEmployeesCode;
    EmployeeCode stepEmployeesCode;
    EmployeeCode departmentEmployeesCode;

    @BeforeEach
    void init() {
        employeesCodeRepository = mock(EmployeeCodeRepository.class);
        employeesCodeService = new EmployeeCodeService(employeesCodeRepository);

        typeEmployeesCode = EmployeeCode.builder()
                .id(1L)
                .code("구분")
                .employeeCodeType(EmployeeCodeType.TYPE)
                .used(Used.Y)
                .build();

        positionEmployeesCode = EmployeeCode.builder()
                .id(2L)
                .code("직위")
                .employeeCodeType(EmployeeCodeType.POSITION)
                .used(Used.Y)
                .build();

        rankEmployeesCode = EmployeeCode.builder()
                .id(3L)
                .code("직급")
                .employeeCodeType(EmployeeCodeType.RANK)
                .used(Used.Y)
                .build();

        stepEmployeesCode = EmployeeCode.builder()
                .id(4L)
                .code("호봉")
                .employeeCodeType(EmployeeCodeType.STEP)
                .used(Used.Y)
                .build();

        departmentEmployeesCode = EmployeeCode.builder()
                .id(5L)
                .code("근무부서")
                .employeeCodeType(EmployeeCodeType.DEPARTMENT)
                .used(Used.Y)
                .build();

        when(employeesCodeRepository.findById(1L)).thenReturn(Optional.of(typeEmployeesCode));
        when(employeesCodeRepository.findById(2L)).thenReturn(Optional.of(positionEmployeesCode));
        when(employeesCodeRepository.findById(3L)).thenReturn(Optional.of(rankEmployeesCode));
        when(employeesCodeRepository.findById(4L)).thenReturn(Optional.of(stepEmployeesCode));
        when(employeesCodeRepository.findById(5L)).thenReturn(Optional.of(departmentEmployeesCode));
        when(employeesCodeRepository.findById(6L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        EmployeeCode findType = employeesCodeService.findById(1L);
        isEqualToEmployeesCode(typeEmployeesCode, findType);

        EmployeeCode findPosition = employeesCodeService.findById(2L);
        isEqualToEmployeesCode(positionEmployeesCode, findPosition);

        EmployeeCode findRank = employeesCodeService.findById(3L);
        isEqualToEmployeesCode(rankEmployeesCode, findRank);

        EmployeeCode findStep = employeesCodeService.findById(4L);
        isEqualToEmployeesCode(stepEmployeesCode, findStep);

        EmployeeCode findDepartment = employeesCodeService.findById(5L);
        isEqualToEmployeesCode(departmentEmployeesCode, findDepartment);

        assertThrows(EntityNotFoundException.class, () -> employeesCodeService.findById(6L));
        verify(employeesCodeRepository, times(6)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        EmployeeCode updateData = EmployeeCode.builder()
                .code("구분 수정")
                .employeeCodeType(EmployeeCodeType.TYPE)
                .used(Used.Y)
                .build();

        Long updateId = employeesCodeService.update(1L, updateData);
        verify(employeesCodeRepository, times(1)).findById(anyLong());

        EmployeeCode update = employeesCodeService.findById(updateId);
        isEqualToEmployeesCode(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        employeesCodeService.delete(ids);
        verify(employeesCodeRepository, times(5)).findById(anyLong());

        EmployeeCode delete1 = employeesCodeService.findById(1L);
        EmployeeCode delete2 = employeesCodeService.findById(2L);
        EmployeeCode delete3 = employeesCodeService.findById(3L);
        EmployeeCode delete4 = employeesCodeService.findById(4L);
        EmployeeCode delete5 = employeesCodeService.findById(5L);

        assertThat(delete1.getUsed()).isEqualTo(Used.N);
        assertThat(delete2.getUsed()).isEqualTo(Used.N);
        assertThat(delete3.getUsed()).isEqualTo(Used.N);
        assertThat(delete4.getUsed()).isEqualTo(Used.N);
        assertThat(delete5.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualToEmployeesCode(EmployeeCode employeesCode, EmployeeCode findEmployeesCode) {
        assertThat(employeesCode.getCode()).isEqualTo(findEmployeesCode.getCode());
        assertThat(employeesCode.getEmployeeCodeType()).isEqualTo(findEmployeesCode.getEmployeeCodeType());
        assertThat(employeesCode.getUsed()).isEqualTo(findEmployeesCode.getUsed());
    }
}
