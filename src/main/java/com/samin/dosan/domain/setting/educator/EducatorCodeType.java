package com.samin.dosan.domain.setting.educator;

import lombok.Getter;

@Getter
public enum EducatorCodeType {
    TYPE("구분"), CHARGE("담당"), BELONG("소속"), BRANCH("지부");

    private final String description;

    EducatorCodeType(String description) {
        this.description = description;
    }
}
