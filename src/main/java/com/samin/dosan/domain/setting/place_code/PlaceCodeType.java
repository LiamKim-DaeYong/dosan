package com.samin.dosan.domain.setting.place_code;

import lombok.Getter;

@Getter
public enum PlaceCodeType {
    TRAINING("수련 장소"), EXPLR("탐방지");

    private final String description;

    PlaceCodeType(String description) {
        this.description = description;
    }
}
