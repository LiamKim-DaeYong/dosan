package com.samin.dosan.domain.homepage.media_archive;

import lombok.Getter;

@Getter
public enum MediaArchiveType {
    PROMOTIONAL_VIDEO("홍보동영상"), WEBTOON("만화퇴계");

    private final String description;

    MediaArchiveType(String description) {
        this.description = description;
    }
}
