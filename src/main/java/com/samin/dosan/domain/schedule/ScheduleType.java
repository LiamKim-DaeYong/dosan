package com.samin.dosan.domain.schedule;

import lombok.Getter;

@Getter
public enum ScheduleType {
    CHAIRMAN("이사장"), DIRECTOR("원장"), ETC("기타");

    private final String description;

    ScheduleType(String description) {
        this.description = description;
    }
}
