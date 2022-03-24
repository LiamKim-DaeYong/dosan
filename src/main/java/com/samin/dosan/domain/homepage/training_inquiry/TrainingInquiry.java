package com.samin.dosan.domain.homepage.training_inquiry;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.homepage.type.CheckType;
import com.samin.dosan.domain.homepage.type.TrainingType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "homepage_training_inquiry")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainingInquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advice_id")
    private Long id;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String agree;

    @Column(length = 1)
    private CheckType status;

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
    private TrainingType trainingInquiryType;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void updateStatus() {
        this.status = CheckType.Y;
    }

    public void delete() {
        this.used = Used.N;
    }
}
