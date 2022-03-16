package com.samin.dosan.domain.homepage.advice.dto;

import com.samin.dosan.domain.homepage.advice.Advice;
import lombok.Data;

import java.time.LocalDate;


@Data
public class AdviceRequest {
    private Long id;

    private String agree;

    private String status;

    private String insttNm;

    private String depart;

    private String applicant;

    private String phone;

    private String adviceType;

    private LocalDate regDt = LocalDate.now();

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
