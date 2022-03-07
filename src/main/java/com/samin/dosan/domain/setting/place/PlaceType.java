package com.samin.dosan.domain.setting.place;

import lombok.Getter;

@Getter
public enum PlaceType {
    TRAINING("수련 장소"), EXPLR("탐방지");

    private final String description;

    PlaceType(String description) {
        this.description = description;
    }
}
