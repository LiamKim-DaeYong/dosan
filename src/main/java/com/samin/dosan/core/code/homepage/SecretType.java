package com.samin.dosan.core.code.homepage;

import com.samin.dosan.core.code.EnumNameParser;
import lombok.Getter;

@Getter
public enum SecretType implements EnumNameParser {
    Y("설정"), N("미설정");

    private final String description;

    SecretType(String description) {
        this.description = description;
    }
}
