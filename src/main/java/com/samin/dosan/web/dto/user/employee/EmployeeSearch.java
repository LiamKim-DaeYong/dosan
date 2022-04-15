package com.samin.dosan.web.dto.user.employee;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeSearch {

    private String userId;

    private String userNm;

    private String phoneNum;

    @QueryProjection
    public EmployeeSearch(String userId, String userNm, String phoneNum) {
        this.userId = userId;
        this.userNm = userNm;
        this.phoneNum = phoneNum;
    }
}
