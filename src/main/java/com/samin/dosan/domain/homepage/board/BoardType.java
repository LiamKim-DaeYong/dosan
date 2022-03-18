package com.samin.dosan.domain.homepage.board;

import lombok.Getter;

@Getter
public enum BoardType {
    NOTICE("공지사항"), REPORTED_NEWS("보도자료"), PHOTO_GALLERY("포토갤러리");

    private final String description;

    BoardType(String description) {
        this.description = description;
    }
}
