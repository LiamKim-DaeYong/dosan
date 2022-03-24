package com.samin.dosan.web.dto.business_trip_report;

import com.samin.dosan.domain.business_trip_report.AccommodationType;
import com.samin.dosan.domain.business_trip_report.ApprovalType;
import com.samin.dosan.domain.business_trip_report.MealType;
import com.samin.dosan.domain.user.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class BusinessTripReportSave {

    private Long id;

    private User user;

    @NotBlank
    private String title;

    @NotBlank
    private String businessPlace;

    @NotBlank
    private String fromCity;

    @NotBlank
    private String fromCounty;

    @NotBlank
    private String toCity;

    @NotBlank
    private String toCounty;

    @NotBlank
    private String businessPurpose;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate businessStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate businessEndDate;

    @NotBlank
    private String businessPurposePerformance;

    private String otherReference;

    private MealType mealType;

    private AccommodationType accommodationType;

    private ApprovalType approvalType;
}
