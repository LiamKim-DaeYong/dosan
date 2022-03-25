package com.samin.dosan.domain.business_trip_report;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.dto.business_trip_report.BusinessTripReportSave;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "business_trip_report")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessTripReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title; //제목

    @Column(nullable = false)
    private String businessPlace; //출장지

    @Column(nullable = false)
    private String fromCity; //츨발지역 시/도

    @Column(nullable = false)
    private String fromCounty; //출발지역 시군구

    @Column(nullable = false)
    private String toCity; //도착지역 시/도

    @Column(nullable = false)
    private String toCounty; //도착지역 시군구

    @Column(nullable = false)
    private String businessPurpose; //출장목적

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private LocalDate businessStartDate; //출장기간 시작일

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private LocalDate businessEndDate; //출장기간 종료일

    @Column(columnDefinition = "TEXT", nullable = false)
    private String businessPurposePerformance; //출장목적 수행 현황

    @Column(columnDefinition = "TEXT")
    private String otherReference; //기타 참고사항

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType; //식사제공

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccommodationType accommodationType; //숙박여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalType approvalType; //결재상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Used used;

    //증빙서류 리스트

    /*================== Business Logic ==================*/
    public static BusinessTripReport of(BusinessTripReportSave saveData) {
        BusinessTripReport businessTripReport = new BusinessTripReport();
        businessTripReport.used = Used.Y;
        businessTripReport.user = saveData.getUser();

        businessTripReport.title = saveData.getTitle();
        businessTripReport.businessPlace = saveData.getBusinessPlace();
        businessTripReport.fromCity = saveData.getFromCity();
        businessTripReport.fromCounty = saveData.getFromCounty();
        businessTripReport.toCity = saveData.getToCity();
        businessTripReport.toCounty = saveData.getToCounty();
        businessTripReport.businessPurpose = saveData.getBusinessPurpose();
        businessTripReport.businessStartDate = saveData.getBusinessStartDate();
        businessTripReport.businessEndDate = saveData.getBusinessEndDate();
        businessTripReport.businessPurposePerformance = saveData.getBusinessPurposePerformance();
        businessTripReport.otherReference = saveData.getOtherReference();
        businessTripReport.mealType = saveData.getMealType();
        businessTripReport.accommodationType = saveData.getAccommodationType();
        businessTripReport.approvalType = saveData.getApprovalType();

        return businessTripReport;
    }

    public void update(BusinessTripReportSave updateData) {
        this.title = updateData.getTitle();
        this.businessPlace = updateData.getBusinessPlace();
        this.fromCity = updateData.getFromCity();
        this.fromCounty = updateData.getFromCounty();
        this.toCity = updateData.getToCity();
        this.toCounty = updateData.getToCounty();
        this.businessPurpose = updateData.getBusinessPurpose();
        this.businessStartDate = updateData.getBusinessStartDate();
        this.businessEndDate = updateData.getBusinessEndDate();
        this.businessPurposePerformance = updateData.getBusinessPurposePerformance();
        this.otherReference = updateData.getOtherReference();
        this.mealType = updateData.getMealType();
        this.accommodationType = updateData.getAccommodationType();
    }

    public void delete() {
        this.used = Used.N;
    }

    public void draft(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }
}
