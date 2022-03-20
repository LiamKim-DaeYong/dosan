package com.samin.dosan.domain.setting.former;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCode;
import com.samin.dosan.domain.setting.former_job_code.FormerJobCodeService;
import com.samin.dosan.domain.setting.former_job_code.repository.FormerJobCodeRepository;
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

class FormerServiceTest {

    FormerJobCodeRepository formerJobCodeRepository;
    FormerJobCodeService formerJobCodeService;

    FormerJobCode former;

    @BeforeEach
    void init() {
        formerJobCodeRepository = mock(FormerJobCodeRepository.class);
        formerJobCodeService = new FormerJobCodeService(formerJobCodeRepository);

        former = FormerJobCode.builder()
                .id(1L)
                .formerNm("전직")
                .used(Used.Y)
                .build();

        when(formerJobCodeRepository.findById(1L)).thenReturn(Optional.of(former));
        when(formerJobCodeRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        FormerJobCode findFormer = formerJobCodeService.findById(1L);
        isEqualFormer(former, findFormer);

        assertThrows(EntityNotFoundException.class, () -> formerJobCodeService.findById(2L));
        verify(formerJobCodeRepository, times(2)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        FormerJobCode updateData = FormerJobCode.builder()
                .formerNm("전직 수정")
                .used(Used.Y)
                .build();

        Long updateId = formerJobCodeService.update(1L, updateData);
        verify(formerJobCodeRepository, times(1)).findById(anyLong());

        FormerJobCode update = formerJobCodeService.findById(updateId);
        isEqualFormer(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L);
        formerJobCodeService.delete(ids);
        verify(formerJobCodeRepository, times(1)).findById(anyLong());

        FormerJobCode delete = formerJobCodeService.findById(1L);

        Assertions.assertThat(delete.getUsed()).isEqualTo(Used.N);
    }


    private void isEqualFormer(FormerJobCode former, FormerJobCode findFormer) {
        Assertions.assertThat(former.getFormerNm()).isEqualTo(findFormer.getFormerNm());
        Assertions.assertThat(former.getUsed()).isEqualTo(findFormer.getUsed());
    }
}
