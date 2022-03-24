package com.samin.dosan.domain.business_trip_report;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum ApprovalType implements EnumNameParser {
    SAVE("저장"), DRAFT("승인대기"), APPROVAL("승인"), RETURN("반려");

    private final String description;

    ApprovalType(String description) {
        this.description = description;
    }
}
