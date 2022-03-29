package com.samin.dosan.domain.clients;

import com.samin.dosan.core.utils.EnumNameParser;
import lombok.Getter;

@Getter
public enum ClientType implements EnumNameParser {
    ELEMENTARY_SCHOOL_STUDENTS("초등학생", "student"),
    MIDDLE_SCHOOL_STUDENTS("중학생", "student"),
    HIGH_SCHOOL_STUDENTS("고등학생", "student"),
    UNIVERSITY_STUDENTS("대학생", "adult"),
    THE_PUBLIC("일반인", "adult"),
    FAMILY("학부모/가족", "adult"),
    FOREIGNER("외국인", "adult"),
    TEACHER("교원", "worker"),
    PUBLIC_OFFCIAL("공무원", "worker"),
    ENTREPRENEUR("기업인", "worker"),
    MILITARY_PERSONNEL("군장병", "worker");

    private final String description;

    private final String group;

    ClientType(String description, String group) {
        this.description = description;
        this.group = group;
    }
}
