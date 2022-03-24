package com.samin.dosan.domain.homepage.type;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum PostType implements EnumNameParser {
    Y("게시"), N("미게시");

    private final String description;

    PostType(String description) {
        this.description = description;
    }
}
