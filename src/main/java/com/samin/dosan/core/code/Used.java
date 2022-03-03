package com.samin.dosan.core.code;

import lombok.Getter;

@Getter
public enum Used {
    Y("사용"), N("미사용");

    private final String description;

    Used(String description) {
        this.description = description;
    }
}
