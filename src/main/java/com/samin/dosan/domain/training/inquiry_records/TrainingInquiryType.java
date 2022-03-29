package com.samin.dosan.domain.training.inquiry_records;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum TrainingInquiryType implements EnumNameParser {

    ENTRY("입교 수련"), SCHOOL("학교 수련");

    private final String description;

    TrainingInquiryType(String description) {
        this.description = description;
    }
}
