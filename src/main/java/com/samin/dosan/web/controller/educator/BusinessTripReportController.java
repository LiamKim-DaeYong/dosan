package com.samin.dosan.web.controller.educator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educator/business-trip-report")
public class BusinessTripReportController {

//    private final BusinessTripReportService businessTripReportService;

    @GetMapping
    public String mainView() {
        return "/educator/business_trip_report/mainView";
    }

    @GetMapping("/detail")
    public String detailView() {
        return "/educator/business_trip_report/detailView";
    }

    @GetMapping("/add")
    public String addView() {
        return "/educator/business_trip_report/addView";
    }

    @GetMapping("/edit")
    public String editView() {
        return "/educator/business_trip_report/editView";
    }
}
