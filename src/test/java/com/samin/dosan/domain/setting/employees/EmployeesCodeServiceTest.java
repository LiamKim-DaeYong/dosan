package com.samin.dosan.domain.setting.employees;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.employees.repository.EmployeesCodeRepository;
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

    EmployeesCodeRepository employeesCodeRepository;
    EmployeesCodeService employeesCodeService;

    EmployeesCode typeEmployeesCode;
    EmployeesCode positionEmployeesCode;
    EmployeesCode rankEmployeesCode;
    EmployeesCode stepEmployeesCode;
    EmployeesCode departmentEmployeesCode;

    @BeforeEach
    void init() {
        employeesCodeRepository = mock(EmployeesCodeRepository.class);
        employeesCodeService = new EmployeesCodeService(employeesCodeRepository);

        typeEmployeesCode = EmployeesCode.builder()
                .id(1L)
                .code("구분")
                .employeesCodeType(EmployeesCodeType.TYPE)
                .used(Used.Y)
                .build();

        positionEmployeesCode = EmployeesCode.builder()
                .id(2L)
                .code("직위")
                .employeesCodeType(EmployeesCodeType.POSITION)
                .used(Used.Y)
                .build();

        rankEmployeesCode = EmployeesCode.builder()
                .id(3L)
                .code("직급")
                .employeesCodeType(EmployeesCodeType.RANK)
                .used(Used.Y)
                .build();

        stepEmployeesCode = EmployeesCode.builder()
                .id(4L)
                .code("호봉")
                .employeesCodeType(EmployeesCodeType.STEP)
                .used(Used.Y)
                .build();

        departmentEmployeesCode = EmployeesCode.builder()
                .id(5L)
                .code("근무부서")
                .employeesCodeType(EmployeesCodeType.DEPARTMENT)
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
        EmployeesCode findType = employeesCodeService.findById(1L);
        isEqualToEmployeesCode(typeEmployeesCode, findType);

        EmployeesCode findPosition = employeesCodeService.findById(2L);
        isEqualToEmployeesCode(positionEmployeesCode, findPosition);

        EmployeesCode findRank = employeesCodeService.findById(3L);
        isEqualToEmployeesCode(rankEmployeesCode, findRank);

        EmployeesCode findStep = employeesCodeService.findById(4L);
        isEqualToEmployeesCode(stepEmployeesCode, findStep);

        EmployeesCode findDepartment = employeesCodeService.findById(5L);
        isEqualToEmployeesCode(departmentEmployeesCode, findDepartment);

        assertThrows(EntityNotFoundException.class, () -> employeesCodeService.findById(6L));
        verify(employeesCodeRepository, times(6)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        EmployeesCode updateData = EmployeesCode.builder()
                .code("구분 수정")
                .employeesCodeType(EmployeesCodeType.TYPE)
                .used(Used.Y)
                .build();

        Long updateId = employeesCodeService.update(1L, updateData);
        verify(employeesCodeRepository, times(1)).findById(anyLong());

        EmployeesCode update = employeesCodeService.findById(updateId);
        isEqualToEmployeesCode(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        employeesCodeService.delete(ids);
        verify(employeesCodeRepository, times(5)).findById(anyLong());

        EmployeesCode delete1 = employeesCodeService.findById(1L);
        EmployeesCode delete2 = employeesCodeService.findById(2L);
        EmployeesCode delete3 = employeesCodeService.findById(3L);
        EmployeesCode delete4 = employeesCodeService.findById(4L);
        EmployeesCode delete5 = employeesCodeService.findById(5L);

        assertThat(delete1.getUsed()).isEqualTo(Used.N);
        assertThat(delete2.getUsed()).isEqualTo(Used.N);
        assertThat(delete3.getUsed()).isEqualTo(Used.N);
        assertThat(delete4.getUsed()).isEqualTo(Used.N);
        assertThat(delete5.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualToEmployeesCode(EmployeesCode employeesCode, EmployeesCode findEmployeesCode) {
        assertThat(employeesCode.getCode()).isEqualTo(findEmployeesCode.getCode());
        assertThat(employeesCode.getEmployeesCodeType()).isEqualTo(findEmployeesCode.getEmployeesCodeType());
        assertThat(employeesCode.getUsed()).isEqualTo(findEmployeesCode.getUsed());
    }
}
