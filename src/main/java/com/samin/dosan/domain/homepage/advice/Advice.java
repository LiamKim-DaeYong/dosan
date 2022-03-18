package com.samin.dosan.domain.homepage.advice;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advice_id")
    private Long id;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String agree;

    @Column(length = 1)
    private String status;

    @Column(length = 100)
    private String insttNm;

    @Column(length = 50)
    private String depart;

    @Column(length = 100, nullable = false)
    private String applicant;

    @Column(length = 15, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AdviceType adviceType;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void updateStatus() {
        this.status = "Y";
    }

    public void delete() {
        this.used = Used.N;
    }
}
