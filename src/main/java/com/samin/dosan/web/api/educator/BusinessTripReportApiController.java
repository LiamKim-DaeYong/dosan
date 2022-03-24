package com.samin.dosan.web.api.educator;

import com.samin.dosan.domain.business_trip_report.ApprovalType;
import com.samin.dosan.domain.business_trip_report.BusinessTripReport;
import com.samin.dosan.domain.business_trip_report.BusinessTripReportService;
import com.samin.dosan.domain.user.UserService;
import com.samin.dosan.web.dto.business_trip_report.BusinessTripReportSave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educator/business-trip-report")
public class BusinessTripReportApiController {

    private final BusinessTripReportService businessTripReportService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody BusinessTripReportSave businessTripReportSave) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        businessTripReportSave.setUser(userService.findById(username));

        Long id = businessTripReportService.save(BusinessTripReport.of(businessTripReportSave));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody BusinessTripReportSave businessTripReportSave) {
        businessTripReportService.update(id, businessTripReportSave);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}/draft")
    public ResponseEntity draft(@PathVariable Long id, @Valid @RequestBody Map<String, String> draftData) {
        String approval = draftData.get("approvalType");
        businessTripReportService.draft(id, ApprovalType.valueOf(approval));

        return ResponseEntity.ok(id);
    }
}
