package com.samin.dosan.domain.user;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("관리자"), EMPLOYEES("임직원"), EDUCATOR("지도위원");

    private final String description;

    UserType(String description) {
        this.description = description;
    }
}
