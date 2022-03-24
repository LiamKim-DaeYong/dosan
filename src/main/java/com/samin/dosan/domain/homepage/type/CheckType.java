package com.samin.dosan.domain.homepage.type;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum CheckType implements EnumNameParser {
    Y("확인완료"), N("미확인");

    private final String description;

    CheckType(String description) {
        this.description = description;
    }
}
