package com.samin.dosan.domain.homepage.type;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum TrainingType implements EnumNameParser {
    ALL("전체"),
    SUNBI("찾아가는 선비체험"),
    ADMISSION("입교과정"),
    FAMILY("가족"),
    ORDINARY("일반인"),
    CHECK("확인완료"),
    UNCHECK("미확인");

    private final String description;

    TrainingType(String description) {
        this.description = description;
    }
}
