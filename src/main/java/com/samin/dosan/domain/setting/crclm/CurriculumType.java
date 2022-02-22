package com.samin.dosan.domain.setting.crclm;

public enum CurriculumType {
    ENTRY("입교"), SCHOOL("학교"), Explor("탐방지");

    private String label;

    CurriculumType(String label) {
        this.label = label;
    }
}
