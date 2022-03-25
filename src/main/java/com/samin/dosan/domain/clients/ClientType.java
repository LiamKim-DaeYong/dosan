package com.samin.dosan.domain.clients;

import com.samin.dosan.core.utils.enums.EnumNameParser;
import lombok.Getter;

@Getter
public enum ClientType implements EnumNameParser {
    ELEMENTARY_SCHOOL_STUDENTS("초등학생"),
    MIDDLE_SCHOOL_STUDENTS("중학생"),
    HIGH_SCHOOL_STUDENTS("고등학생"),
    UNIVERSITY_STUDENTS("대학생"),
    THE_PUBLIC("일반인"),
    FAMILY("학부모/가족"),
    FOREIGNER("외국인"),
    TEACHER("교원"),
    PUBLIC_OFFCIAL("공무원"),
    ENTREPRENEUR("기업인"),
    MILITARY_PERSONNEL("군장병");

    private final String description;

    ClientType(String description) {
        this.description = description;
    }
}
