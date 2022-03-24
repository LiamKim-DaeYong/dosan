package com.samin.dosan.domain.business_trip_report;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum MealType implements EnumNameParser {
    ONE_MEAL("1식"), TWO_MEALS("2식"), THREE_MEALS("3식");

    private final String description;

    MealType(String description) {
        this.description = description;
    }
}
