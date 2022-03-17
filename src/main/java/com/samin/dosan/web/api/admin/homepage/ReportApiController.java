package com.samin.dosan.web.api.admin.homepage;

import com.samin.dosan.domain.homepage.report.ReportService;
import com.samin.dosan.domain.homepage.report.dto.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ReportApiController {

    private final ReportService reportService;

    @PostMapping("/save")
    public RedirectView reportSave(@ModelAttribute ReportRequest request) {
        Long id = reportService.reportSave_admin(request);

        return new RedirectView("/report/detail/"+id);
    }

    @PostMapping("/delete")
    public boolean reportListDelete(@RequestBody List<Long> idList) {
        boolean result = false;
        if (reportService.reportDelete_admin(idList)) {
            result = true;
        }

        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean reportDelete(@PathVariable("id") Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);

        boolean result = false;
        if (reportService.reportDelete_admin(idList)) {
            result = true;
        }

        return result;
    }
}
