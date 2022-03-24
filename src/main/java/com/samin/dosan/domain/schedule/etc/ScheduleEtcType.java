package com.samin.dosan.domain.schedule.etc;

import lombok.Getter;

@Getter
public enum ScheduleEtcType {
    CHAIRMAN("이사장"), DIRECTOR("원장"), ETC("기타"), NONE("미배정");

    private final String description;

    ScheduleEtcType(String description) {
        this.description = description;
    }
}
