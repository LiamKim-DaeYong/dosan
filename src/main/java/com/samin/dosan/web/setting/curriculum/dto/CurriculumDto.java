package com.samin.dosan.web.setting.curriculum.dto;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CurriculumDto {

    private Long id;

    private String subject;

    private String content;

    private String curriculumType;

    private String createdBy;

    private LocalDate createdAt;

    public CurriculumDto(Curriculum curriculum) {
        this.id = curriculum.getId();
        this.subject = curriculum.getSubject();
        this.content = curriculum.getContent();
        this.curriculumType = curriculum.getCurriculumType().getDescription();
        this.createdBy = curriculum.getCreatedBy();
        this.createdAt = curriculum.getCreatedAt().toLocalDate();
    }
}
