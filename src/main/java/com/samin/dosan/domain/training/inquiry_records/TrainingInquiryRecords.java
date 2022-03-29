package com.samin.dosan.domain.training.inquiry_records;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.clients.Clients;
import com.samin.dosan.domain.training.training_target.TrainingTarget;
import com.samin.dosan.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    protected Clients clients = new Clients();

    @OneToMany(mappedBy = "trainingInquiryRecords", cascade = CascadeType.ALL)
    private List<TrainingTarget> trainingTargetList = new ArrayList<>();

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

    @Column(length = 10, nullable = false)
    private InquiryRecordsStatus status;

    /*================== Business Logic ==================*/

    public void setTrainingInquiryType(TrainingInquiryType trainingInquiryType) {
        this.trainingInquiryType = trainingInquiryType;
    }

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

        trainingInquiryRecords.status = InquiryRecordsStatus.SAVE;

        return trainingInquiryRecords;
    }

    @Builder(builderMethodName = "test")
    public TrainingInquiryRecords(Long id, Clients clients, TrainingInquiryType trainingInquiryType, String contactNm,
                                  String contactPhoneNum, String contactOfficeNum, String contactGender, String contactEmail,
                                  String trainingStartDate, String trainingEndDate, String motivation, String purpose,
                                  String request, String trainingBefore, String trainingAfter, String note, InquiryRecordsStatus status) {
        this.id = id;
        this.clients = clients;
        this.trainingInquiryType = trainingInquiryType;
        this.contactNm = contactNm;
        this.contactPhoneNum = contactPhoneNum;
        this.contactOfficeNum = contactOfficeNum;
        this.contactGender = contactGender;
        this.contactEmail = contactEmail;
        this.trainingStartDate = trainingStartDate;
        this.trainingEndDate = trainingEndDate;
        this.motivation = motivation;
        this.purpose = purpose;
        this.request = request;
        this.trainingBefore = trainingBefore;
        this.trainingAfter = trainingAfter;
        this.note = note;
        this.status = status;
    }

    public void update(TrainingInquiryRecords updateData) {
        this.trainingStartDate = updateData.trainingStartDate;
        this.trainingEndDate = updateData.trainingEndDate;
    }

    public void delete() {
        this.status = InquiryRecordsStatus.CANCEL;
    }
}
