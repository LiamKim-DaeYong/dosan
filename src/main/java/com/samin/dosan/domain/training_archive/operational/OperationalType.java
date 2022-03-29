package com.samin.dosan.domain.training_archive.operational;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum OperationalType implements EnumNameParser {
    ENTRY("입교"), SCHOOL("학교");

    private final String description;

    OperationalType(String description) {
        this.description = description;
    }
}
