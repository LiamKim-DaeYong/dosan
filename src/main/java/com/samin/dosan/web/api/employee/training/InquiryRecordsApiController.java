package com.samin.dosan.web.api.employee.training;

import com.samin.dosan.core.parameter.SearchParam;
import com.samin.dosan.domain.clients.Clients;
import com.samin.dosan.domain.clients.repository.ClientsService;
import com.samin.dosan.domain.training.inquiry_records.repository.InquiryRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee/training/inquiry_records")
public class InquiryRecordsApiController {

    private final InquiryRecordsService inquiryRecordsService;
}
