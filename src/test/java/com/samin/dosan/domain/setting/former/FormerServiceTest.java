package com.samin.dosan.domain.setting.former;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.former.repository.FormerRepository;
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

    FormerRepository formerRepository;
    FormerService formerService;

    Former former;

    @BeforeEach
    void init() {
        formerRepository = mock(FormerRepository.class);
        formerService = new FormerService(formerRepository);

        former = Former.builder()
                .id(1L)
                .formerName("전직")
                .used(Used.Y)
                .build();

        when(formerRepository.findById(1L)).thenReturn(Optional.of(former));
        when(formerRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        Former findFormer = formerService.findById(1L);
        isEqualFormer(former, findFormer);

        assertThrows(EntityNotFoundException.class, () -> formerService.findById(2L));
        verify(formerRepository, times(2)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        Former updateData = Former.builder()
                .formerName("전직 수정")
                .used(Used.Y)
                .build();

        Long updateId = formerService.update(1L, updateData);
        verify(formerRepository, times(1)).findById(anyLong());

        Former update = formerService.findById(updateId);
        isEqualFormer(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L);
        formerService.delete(ids);
        verify(formerRepository, times(1)).findById(anyLong());

        Former delete = formerService.findById(1L);

        Assertions.assertThat(delete.getUsed()).isEqualTo(Used.N);
    }


    private void isEqualFormer(Former former, Former findFormer) {
        Assertions.assertThat(former.getFormerName()).isEqualTo(findFormer.getFormerName());
        Assertions.assertThat(former.getUsed()).isEqualTo(findFormer.getUsed());
    }
}
