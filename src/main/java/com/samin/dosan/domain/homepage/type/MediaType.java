package com.samin.dosan.domain.homepage.type;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum MediaType implements EnumNameParser {
    PROMOTIONAL_VIDEO("홍보동영상"), WEBTOON("만화퇴계");

    private final String description;

    MediaType(String description) {
        this.description = description;
    }
}
