package com.samin.dosan.web.member.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserDto {

    private String userNm;

    private String gender;

    private LocalDate appointmentDt;

    private String workSec;

    private String branch;

    private String phoneNum;

    private LocalDate retirementDt;

    @QueryProjection
    public UserDto(String userNm, String gender, String phoneNum) {
        this.userNm = userNm;
        this.gender = gender;
        this.phoneNum = phoneNum;
    }
}
