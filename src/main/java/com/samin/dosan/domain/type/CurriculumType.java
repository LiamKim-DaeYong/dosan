package com.samin.dosan.domain.type;

import lombok.Getter;

@Getter
public enum CurriculumType {
    ENTRY("입교"), SCHOOL("학교"), EXPLOR("탐방지");

    private final String description;

    CurriculumType(String description) {
        this.description = description;
    }
}
