package com.samin.dosan.domain.homepage.advice.dto;

import com.samin.dosan.domain.homepage.advice.Advice;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdviceChangeCheckRequest {

    private Long id;

    private String agree;

    private String status;

    private String insttNm;

    private String depart;

    private String applicant;

    private String phone;

    private String adviceType;

    private LocalDate regDt;

    public AdviceChangeCheckRequest(Advice advice, String status) {
        this.id = advice.getId();
        this.agree = advice.getAgree();
        this.status = status;
        this.insttNm = advice.getInsttNm();
        this.depart = advice.getDepart();
        this.applicant = advice.getApplicant();
        this.phone = advice.getPhone();
        this.adviceType = advice.getAdviceType();
        this.regDt = advice.getRegDt();
    }

    public Advice toEntity() {
        return Advice.builder()
                .id(this.id)
                .agree(this.agree)
                .status(this.status)
                .insttNm(this.insttNm)
                .applicant(this.applicant)
                .phone(this.phone)
                .depart(this.depart)
                .adviceType(this.adviceType)
                .regDt(this.regDt)
                .build();
    }
}
