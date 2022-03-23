package com.samin.dosan.core.code.homepage;

import com.samin.dosan.core.code.EnumNameParser;
import lombok.Getter;

@Getter
public enum CheckType implements EnumNameParser {
    Y("확인완료"), N("미확인");

    private final String description;

    CheckType(String description) {
        this.description = description;
    }
}
