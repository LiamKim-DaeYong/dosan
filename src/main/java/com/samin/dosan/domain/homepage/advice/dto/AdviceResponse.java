package com.samin.dosan.domain.homepage.advice.dto;

import com.samin.dosan.domain.homepage.advice.Advice;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdviceResponse {

    private Long id;

    private String agree;

    private String status;

    private String insttNm;

    private String depart;

    private String applicant;

    private String phone;

    private String adviceType;

    private LocalDate regDt;

    public AdviceResponse(Advice advice) {
        this.id = advice.getId();
        this.agree = advice.getAgree();
        this.status = advice.getStatus();
        this.insttNm = advice.getInsttNm();
        this.depart = advice.getDepart();
        this.applicant = advice.getApplicant();
        this.phone = advice.getPhone();
        this.adviceType = advice.getAdviceType();
        this.regDt = advice.getRegDt();
    }
}
