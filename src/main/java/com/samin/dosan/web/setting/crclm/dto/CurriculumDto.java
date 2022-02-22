package com.samin.dosan.web.setting.crclm.dto;

import com.samin.dosan.domain.setting.crclm.CurriculumType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CurriculumDto {

    private Long id;

    private String subjectNm;

    private String content;

    private String writer;

    private CurriculumType type;

    private LocalDate registDt;
}
