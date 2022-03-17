package com.samin.dosan.domain.homepage.advice;

import lombok.Getter;

@Getter
public enum AdviceType {
    ALL("전체"),
    SUNBI("찾아가는 선비체험"),
    ADMISSION("입교과정"),
    FAMILY("가족"),
    ORDINARY("일반인"),
    CHECK("체크"),
    UNCHECK("체크안됨");

    private final String description;

    AdviceType(String description) {
        this.description = description;
    }
}
