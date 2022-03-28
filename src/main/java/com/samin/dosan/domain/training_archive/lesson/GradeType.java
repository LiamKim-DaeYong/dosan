package com.samin.dosan.domain.training_archive.lesson;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum GradeType implements EnumNameParser {
    ELE_LOWER("초저"),
    ELE_MIDDLE("초중"),
    ELE_UPPER("초고"),
    MIDDLE("중등"),
    HIGH("고등");

    private final String description;

    GradeType(String description) {
        this.description = description;
    }
}
