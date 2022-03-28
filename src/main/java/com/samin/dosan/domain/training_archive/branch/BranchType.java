package com.samin.dosan.domain.training_archive.branch;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum BranchType implements EnumNameParser {
    DAEGU("대구"),
    GYEONGBUK("경북"),
    BUSAN("부산"),
    GYEONGNAM("경남"),
    SEOUL("서울"),
    GYEONGGI("경기"),
    ULSAN("울산"),
    CHUNGCHEON_JEONRA("충청/전라"),
    MANNER("예절");

    private final String description;

    BranchType(String description) {
        this.description = description;
    }
}
