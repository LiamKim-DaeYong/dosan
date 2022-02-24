package com.samin.dosan.web.setting.curriculum.dto;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import com.samin.dosan.domain.type.CurriculumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class CurriculumData {

    private Long id;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;

    private String curriculumType;

    private String createdBy;

    private LocalDate createdAt;

    public CurriculumData(String curriculumType) {
        this.curriculumType = curriculumType;
    }

    public CurriculumData(Curriculum curriculum) {
        this.id = curriculum.getId();
        this.subject = curriculum.getSubject();
        this.content = curriculum.getContent();
        this.curriculumType = curriculum.getCurriculumType().getDescription();
        this.createdBy = curriculum.getCreatedBy();
        this.createdAt = curriculum.getCreatedAt().toLocalDate();
    }

    public Curriculum toEntity() {
        return Curriculum.builder()
                .subject(this.subject)
                .content(this.content)
                .curriculumType(CurriculumType.valueOf(this.curriculumType.toUpperCase()))
                .build();
    }
}
