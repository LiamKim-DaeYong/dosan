package com.samin.dosan.domain.training.inquiry_records;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.clients.Clients;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrainingInquiryRecords extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "training_inquiry_record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    protected Clients clients;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TrainingInquiryType trainingInquiryType;

    @Column(length = 20, nullable = false)
    private String contactNm;

    @Column(length = 15, nullable = false)
    private String contactPhoneNum;

    @Column(length = 15)
    private String contactOfficeNum;

    @Column(length = 10, nullable = false)
    private String contactGender;

    @Column(length = 100, nullable = false)
    private String contactEmail;

    @Column(columnDefinition = "DATE", nullable = false)
    private String trainingStartDate;

    @Column(columnDefinition = "DATE", nullable = false)
    private String trainingEndDate;

    @Column(length = 255)
    private String motivation;

    @Column(columnDefinition = "TEXT")
    private String purpose;

    @Column(columnDefinition = "TEXT")
    private String request;

    @Column(columnDefinition = "TEXT")
    private String trainingBefore;

    @Column(columnDefinition = "TEXT")
    private String trainingAfter;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(length = 20)
    private String register;

    /*================== Business Logic ==================*/
    public static TrainingInquiryRecords of(TrainingInquiryRecords saveData, String type) {
        TrainingInquiryRecords trainingInquiryRecords = new TrainingInquiryRecords();
        trainingInquiryRecords.clients = saveData.clients;
        trainingInquiryRecords.trainingInquiryType = TrainingInquiryType.valueOf(StrUtils.urlToEnumName(type));
        trainingInquiryRecords.contactNm = saveData.contactNm;
        trainingInquiryRecords.contactGender = saveData.contactGender;
        trainingInquiryRecords.contactOfficeNum = saveData.contactOfficeNum;
        trainingInquiryRecords.contactPhoneNum = saveData.contactPhoneNum;
        trainingInquiryRecords.contactEmail = saveData.contactEmail;
        trainingInquiryRecords.trainingStartDate = saveData.trainingStartDate;
        trainingInquiryRecords.trainingEndDate = saveData.trainingEndDate;
        trainingInquiryRecords.motivation = saveData.motivation;
        trainingInquiryRecords.purpose = saveData.purpose;
        trainingInquiryRecords.request = saveData.request;
        trainingInquiryRecords.trainingBefore = saveData.trainingBefore;
        trainingInquiryRecords.trainingAfter = saveData.trainingAfter;
        trainingInquiryRecords.note = saveData.note;

        return trainingInquiryRecords;
    }
}
