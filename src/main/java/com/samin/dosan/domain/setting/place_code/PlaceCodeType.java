package com.samin.dosan.domain.setting.place_code;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum PlaceCodeType implements EnumNameParser {
    TRAINING("수련 장소"), EXPLR("탐방지");

    private final String description;

    PlaceCodeType(String description) {
        this.description = description;
    }
}
