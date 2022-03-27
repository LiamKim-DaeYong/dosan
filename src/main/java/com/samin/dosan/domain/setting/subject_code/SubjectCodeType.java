package com.samin.dosan.domain.setting.subject_code;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum SubjectCodeType implements EnumNameParser {
    ENTRY("입교"), EXPLR("탐방지");

    private final String description;

    SubjectCodeType(String description) {
        this.description = description;
    }
}
