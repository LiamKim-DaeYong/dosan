package com.samin.dosan.domain.type.setting;

import lombok.Getter;

@Getter
public enum CourseType {
    ENTRY("입교"), EXPLR("탐방지");

    private final String description;

    CourseType(String description) {
        this.description = description;
    }
}
