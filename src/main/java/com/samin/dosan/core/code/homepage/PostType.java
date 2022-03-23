package com.samin.dosan.core.code.homepage;

import com.samin.dosan.core.code.EnumNameParser;
import lombok.Getter;

@Getter
public enum PostType implements EnumNameParser {
    Y("게시"), N("미게시");

    private final String description;

    PostType(String description) {
        this.description = description;
    }
}
