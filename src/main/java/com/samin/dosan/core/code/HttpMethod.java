package com.samin.dosan.core.code;

import lombok.Getter;

@Getter
public enum HttpMethod {
    GET("조회"), POST("등록"), PUT("수정"), DELETE("삭제");

    private final String description;

    HttpMethod(String description) {
        this.description = description;
    }
}
