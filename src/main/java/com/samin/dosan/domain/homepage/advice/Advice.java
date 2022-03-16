package com.samin.dosan.domain.homepage.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_advice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

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

    @Column(length = 20, nullable = false)
    private String adviceType;

    @Column(nullable = false)
    private LocalDate regDt;
}
