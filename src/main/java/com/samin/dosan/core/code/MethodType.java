package com.samin.dosan.core.code;

import lombok.Getter;

@Getter
public enum MethodType {
    GET("조회"), POST("등록"), PUT("수정"), DELETE("삭제");

    private final String description;

    MethodType(String description) {
        this.description = description;
    }
}
