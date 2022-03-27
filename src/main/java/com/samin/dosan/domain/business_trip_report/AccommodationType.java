package com.samin.dosan.domain.business_trip_report;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum AccommodationType implements EnumNameParser {
    ACCOMMODATION("숙박업소"), INDIVIDUAL("개인"), THE_DAY("당일일정");

    private final String description;

    AccommodationType(String description) {
        this.description = description;
    }
}
