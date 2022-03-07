package com.samin.dosan.core.code;

import lombok.Getter;

@Getter
public enum Gender {
    MAN("남"), WOMAN("여");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
