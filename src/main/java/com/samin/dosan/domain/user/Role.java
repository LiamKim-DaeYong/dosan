package com.samin.dosan.domain.user;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_EMPLOYEE("임직원"),
    ROLE_EDUCATOR("지도위원"),
    ROLE_MANAGER("임직원(관리자)");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
