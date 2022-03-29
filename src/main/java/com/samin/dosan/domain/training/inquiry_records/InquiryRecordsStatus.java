package com.samin.dosan.domain.training.inquiry_records;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum InquiryRecordsStatus implements EnumNameParser {

    SAVE("저장"), CONFIRM("예약 확정"), CANCEL("예약 취소"), COMPLETE("수련 완료");

    private final String description;

    InquiryRecordsStatus(String description) {
        this.description = description;
    }
}
