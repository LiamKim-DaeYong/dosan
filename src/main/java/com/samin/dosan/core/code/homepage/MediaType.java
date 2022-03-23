package com.samin.dosan.core.code.homepage;

import com.samin.dosan.core.code.EnumNameParser;
import lombok.Getter;

@Getter
public enum MediaType implements EnumNameParser {
    PROMOTIONAL_VIDEO("홍보동영상"), WEBTOON("만화퇴계");

    private final String description;

    MediaType(String description) {
        this.description = description;
    }
}
