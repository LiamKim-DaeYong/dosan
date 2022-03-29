package com.samin.dosan.web.controller.employee.training.inquiry_records;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.Clients;
import com.samin.dosan.domain.clients.repository.ClientsService;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiry;
import com.samin.dosan.domain.homepage.training_inquiry.TrainingInquiryService;
import com.samin.dosan.domain.training.inquiry_records.InquiryRecordsStatus;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryRecords;
import com.samin.dosan.domain.training.inquiry_records.TrainingInquiryType;
import com.samin.dosan.domain.training.inquiry_records.repository.InquiryRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee/training/inquiry_records")
public class InquiryRecordsController {

    private final InquiryRecordsService inquiryRecordsService;
    private final ClientsService clientsService;

    @ModelAttribute("trainingInquiryType")
    public TrainingInquiryType[] trainingInquiryTypes() {
        return TrainingInquiryType.values();
    }

    @ModelAttribute("trainingInquiryStatus")
    public InquiryRecordsStatus[] inquiryRecordsStatuses() {
        return InquiryRecordsStatus.values();
    }

    @GetMapping
    public String mainView(@ModelAttribute SearchParam searchParam, Pageable pageable,
                           Model model) {

        Page<TrainingInquiryRecords> result = inquiryRecordsService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "employee/training/inquiry_records/mainView";
    }

    @GetMapping("/form")
    public String formView() {
        return "employee/training/inquiry_records/addForm :: #addForm";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute TrainingInquiryRecords trainingInquiryRecords) {
        trainingInquiryRecords.setTrainingInquiryType(TrainingInquiryType.ENTRY);

        return "employee/training/inquiry_records/addView";
    }

    @GetMapping("/add/findClients")
    public String findClients(@ModelAttribute SearchParam searchParam, Pageable pageable,
                              Model model) {

        Page<Clients> result = clientsService.findAll(searchParam, pageable);
        model.addAttribute("checked", result.getContent().get(0).getId());
        model.addAttribute("result", result);

        return "employee/training/inquiry_records/findClientsView::#form";
    }
}
