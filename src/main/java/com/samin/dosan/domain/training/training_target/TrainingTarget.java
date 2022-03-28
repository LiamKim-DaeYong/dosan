package com.samin.dosan.domain.training.training_target;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainingTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "training_target_id")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private Long trainingInquiryRecordId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TargetKey key;

    @Column(nullable = false)
    private Integer value;

    /*================== Business Logic ==================*/
}
