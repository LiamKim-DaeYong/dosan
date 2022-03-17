package com.samin.dosan.web.controller.admin.homepage;

import com.samin.dosan.core.parameter.FilterDto;
import com.samin.dosan.domain.homepage.commonfile.CommonFileService;
import com.samin.dosan.domain.homepage.commonfile.dto.CommonFileResponse;
import com.samin.dosan.domain.homepage.report.ReportService;
import com.samin.dosan.domain.homepage.report.dto.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * NOTE : 관리자 - 보도자료 관리
 * */
@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ReportController {

    private final ReportService reportService;
    private final CommonFileService commonFileService;

    @GetMapping
    public String report(@RequestParam(defaultValue = "1") int page, FilterDto filterDto, Model model) {
        Page<ReportResponse> reportList = reportService.getReportList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("reportList", reportList);

        return "homepage/board/report/list";
    }

    @PostMapping("/page")
    public String reportPage(@RequestParam int page, @RequestBody FilterDto filterDto, Model model) {
        Page<ReportResponse> reportList = reportService.getReportList_admin(page, filterDto);

        model.addAttribute("page", page);
        model.addAttribute("filter", filterDto);
        model.addAttribute("reportList", reportList);

        return "homepage/board/report/list :: reportFrag";
    }

    @GetMapping("/regist")
    public String reportRegist() {
        return "homepage/board/report/regist";
    }

    @GetMapping("/detail/{id}")
    public String reportDetail(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        ReportResponse report = reportService.getReport_admin(id);

        if (report.getChildList().size() > 0) {
            for (ReportResponse.ReportFileResponse reportFile : report.getChildList()) {
                fileIdList.add(reportFile.getFileId());
            }
        }

        List<CommonFileResponse> commonFileList = commonFileService.getFiles(fileIdList);

        model.addAttribute("report", report);
        model.addAttribute("fileList", commonFileList);

        return "homepage/board/report/detail";
    }

    @GetMapping("/modify/{id}")
    public String reportModify(@PathVariable("id") Long id, Model model) {
        List<Long> fileIdList = new ArrayList<>();
        ReportResponse report = reportService.getReport_admin(id);

        if (report.getChildList().size() > 0) {
            for (ReportResponse.ReportFileResponse reportFile : report.getChildList()) {
                fileIdList.add(reportFile.getFileId());
            }
        }

        model.addAttribute("report", report);
        model.addAttribute("childList", fileIdList);

        return "homepage/board/report/modify";
    }
}
