package com.samin.dosan.domain.setting.subject_code;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.setting.subject_code.repository.SubjectCodeRepository;
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

class SubjectCodeServiceTest {

    SubjectCodeRepository subjectCodeRepository;
    SubjectCodeService subjectCodeService;

    SubjectCode entryCode;
    SubjectCode explrCode;

    @BeforeEach
    void init() {
        subjectCodeRepository = mock(SubjectCodeRepository.class);
        subjectCodeService = new SubjectCodeService(subjectCodeRepository);

        entryCode = SubjectCode.of("입교", "입교 내용", SubjectCodeType.ENTRY);
        explrCode = SubjectCode.of("탐방지", "탐방지 내용", SubjectCodeType.ENTRY);

        when(subjectCodeRepository.findById(1L)).thenReturn(Optional.of(entryCode));
        when(subjectCodeRepository.findById(2L)).thenReturn(Optional.of(explrCode));
        when(subjectCodeRepository.findById(3L)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("단일 조회 테스트")
    void findById() {
        SubjectCode findEntry = subjectCodeService.findById(1L);
        isEqualToCourse(entryCode, findEntry);

        SubjectCode findExplr = subjectCodeService.findById(2L);
        isEqualToCourse(explrCode, findExplr);

        assertThrows(EntityNotFoundException.class, () -> subjectCodeService.findById(3L));
        verify(subjectCodeRepository, times(3)).findById(anyLong());
    }

    @Test
    @DisplayName("수정 테스트")
    void update() {
        SubjectCode updateData = SubjectCode.of("입교 수정", "입교 내용 수정", SubjectCodeType.ENTRY);

        Long updateId = subjectCodeService.update(1L, updateData);
        verify(subjectCodeRepository, times(1)).findById(anyLong());

        SubjectCode update = subjectCodeService.findById(updateId);
        isEqualToCourse(update, updateData);
    }

    @Test
    @DisplayName("삭제 테스트")
    void delete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        subjectCodeService.delete(ids);
        verify(subjectCodeRepository, times(2)).findById(anyLong());

        SubjectCode delete1 = subjectCodeService.findById(1L);
        SubjectCode delete2 = subjectCodeService.findById(2L);

        assertThat(delete1.getUsed()).isEqualTo(Used.N);
        assertThat(delete2.getUsed()).isEqualTo(Used.N);
    }

    private void isEqualToCourse(SubjectCode subjectCode, SubjectCode findCourse) {
        assertThat(findCourse.getSubject()).isEqualTo(subjectCode.getSubject());
        assertThat(findCourse.getContent()).isEqualTo(subjectCode.getContent());
        assertThat(findCourse.getSubjectCodeType()).isEqualTo(subjectCode.getSubjectCodeType());
        assertThat(findCourse.getUsed()).isEqualTo(subjectCode.getUsed());
    }
}
