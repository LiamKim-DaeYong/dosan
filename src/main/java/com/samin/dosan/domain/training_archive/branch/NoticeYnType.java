package com.samin.dosan.domain.training_archive.branch;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum NoticeYnType implements EnumNameParser {
    Y("공지글 설정"), N("공지글 미설정");

    private final String description;

    NoticeYnType(String description) {
        this.description = description;
    }
}
