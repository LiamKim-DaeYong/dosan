package com.samin.dosan.domain.setting.educator;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.educator.repository.EducatorCodeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EducatorCodeServiceTest {

    EducatorCodeRepository educatorCodeRepository;
    EducatorCodeService educatorCodeService;

    EducatorCode typeEducatorCode;
    EducatorCode chargeEducatorCode;
    EducatorCode belongEducatorCode;
    EducatorCode branchEducatorCode;

    @BeforeEach
    void init() {
        educatorCodeRepository = mock(EducatorCodeRepository.class);
        educatorCodeService = new EducatorCodeService(educatorCodeRepository);

        typeEducatorCode = EducatorCode.builder()
                .id(1L)
                .code("구분")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        chargeEducatorCode = EducatorCode.builder()
                .id(1L)
                .code("담당")
                .educatorCodeType(EducatorCodeType.CHARGE)
                .used(Used.Y)
                .build();

        belongEducatorCode = EducatorCode.builder()
                .id(1L)
                .code("소속")
                .educatorCodeType(EducatorCodeType.BELONG)
                .used(Used.Y)
                .build();

        branchEducatorCode = EducatorCode.builder()
                .id(1L)
                .code("지부")
                .educatorCodeType(EducatorCodeType.BRANCH)
                .used(Used.Y)
                .build();

        when(educatorCodeRepository.findById(1L)).thenReturn(Optional.of(typeEducatorCode));
        when(educatorCodeRepository.findById(2L)).thenReturn(Optional.of(chargeEducatorCode));
        when(educatorCodeRepository.findById(3L)).thenReturn(Optional.of(belongEducatorCode));
        when(educatorCodeRepository.findById(4L)).thenReturn(Optional.of(branchEducatorCode));
        when(educatorCodeRepository.findById(5L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        EducatorCode findType = educatorCodeService.findById(1L);
        isEqualEducator(typeEducatorCode, findType);

        EducatorCode findCharge = educatorCodeService.findById(2L);
        isEqualEducator(chargeEducatorCode, findCharge);

        EducatorCode findBelong = educatorCodeService.findById(3L);
        isEqualEducator(belongEducatorCode, findBelong);

        EducatorCode findBranch = educatorCodeService.findById(4L);
        isEqualEducator(branchEducatorCode, findBranch);

        assertThrows(EntityNotFoundException.class, () -> educatorCodeService.findById(5L));
        verify(educatorCodeRepository, times(5)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        EducatorCode updateData = EducatorCode.builder()
                .code("구분 수정")
                .educatorCodeType(EducatorCodeType.TYPE)
                .used(Used.Y)
                .build();

        Long updateId = educatorCodeService.update(1L, updateData);
        verify(educatorCodeRepository, times(1)).findById(anyLong());

        EducatorCode update = educatorCodeService.findById(updateId);
        isEqualEducator(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L);
        educatorCodeService.delete(ids);
        verify(educatorCodeRepository, times(4)).findById(anyLong());

        EducatorCode delete1 = educatorCodeService.findById(1L);
        EducatorCode delete2 = educatorCodeService.findById(2L);
        EducatorCode delete3 = educatorCodeService.findById(3L);
        EducatorCode delete4 = educatorCodeService.findById(4L);

        Assertions.assertThat(delete1.getUsed()).isEqualTo(Used.N);
        Assertions.assertThat(delete2.getUsed()).isEqualTo(Used.N);
        Assertions.assertThat(delete3.getUsed()).isEqualTo(Used.N);
        Assertions.assertThat(delete4.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualEducator(EducatorCode educatorCode, EducatorCode findEducatorCode) {
        Assertions.assertThat(educatorCode.getCode()).isEqualTo(findEducatorCode.getCode());
        Assertions.assertThat(educatorCode.getEducatorCodeType()).isEqualTo(findEducatorCode.getEducatorCodeType());
        Assertions.assertThat(educatorCode.getUsed()).isEqualTo(findEducatorCode.getUsed());
    }
}
