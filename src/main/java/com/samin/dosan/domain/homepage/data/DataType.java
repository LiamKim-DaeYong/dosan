package com.samin.dosan.domain.homepage.data;

import lombok.Getter;

@Getter
public enum DataType {
    PROMOTION("홍보동영상"), WEBTOON("만화퇴계");

    private final String description;

    DataType(String description) {
        this.description = description;
    }
}
