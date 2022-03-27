package com.samin.dosan.domain.homepage.type;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum SecretType implements EnumNameParser {
    Y("설정"), N("미설정");

    private final String description;

    SecretType(String description) {
        this.description = description;
    }
}
