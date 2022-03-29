package com.samin.dosan.domain.training.training_target;

import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_inquiry_record_id")
    private TrainingInquiryRecords trainingInquiryRecords;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TargetKey key;

    @Column(length = 20, nullable = false)
    private String value;

    /*================== Business Logic ==================*/
}
