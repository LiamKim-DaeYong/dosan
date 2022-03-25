package com.samin.dosan.web.controller.educator;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.business_trip_report.*;
import com.samin.dosan.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/business-trip-report")
public class BusinessTripReportController {

    private final BusinessTripReportService businessTripReportService;
    private final UserService userService;

    @ModelAttribute("mealTypes")
    public MealType[] mealTypes() {
        return MealType.values();
    }

    @ModelAttribute("accommodationTypes")
    public AccommodationType[] accommodationTypes() {
        return AccommodationType.values();
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        Page<BusinessTripReport> result = businessTripReportService.findAll(searchParam, pageable, username);
        model.addAttribute("result", result);

        return "/educator/business_trip_report/mainView";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute BusinessTripReport businessTripReport) {

        return "/educator/business_trip_report/addView";
    }

    @GetMapping("/{id}/detail")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("businessTripReport", businessTripReportService.findById(id));

        return "/educator/business_trip_report/detailView";
    }

    @GetMapping("{id}/edit")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("businessTripReport", businessTripReportService.findById(id));

        return "/educator/business_trip_report/editView";
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i < 1001; i++) {
            BusinessTripReport businessTripReport = BusinessTripReport.builder()
                    .title("제목"+i)
                    .user(userService.findById("educator"))
                    .businessPlace("서울")
                    .fromCity("대구광역시")
                    .fromCounty("남구")
                    .toCity("서울특별시")
                    .toCounty("강남구")
                    .businessPurpose("목적목적목적목적목적")
                    .businessStartDate(LocalDate.now())
                    .businessEndDate(LocalDate.now())
                    .businessPurposePerformance("수행수행수행수행수행수행")
                    .otherReference("")
                    .mealType(MealType.ONE_MEAL)
                    .accommodationType(AccommodationType.INDIVIDUAL)
                    .approvalType(ApprovalType.RETURN)
                    .used(Used.Y)
                    .build();

            businessTripReportService.save(businessTripReport);
        }
    }
}
