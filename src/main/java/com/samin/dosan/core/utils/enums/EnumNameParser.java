package com.samin.dosan.core.utils.enums;

public interface EnumNameParser {
    String getDescription();
    String name();

    default String nameToUrl() {
        return name().toLowerCase()
                .replace("_", "-");
    }
}
