package com.samin.dosan.domain.homepage.report.dto;

import com.samin.dosan.domain.homepage.report.Report;
import com.samin.dosan.domain.homepage.report.file.ReportFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReportResponse {

    private Long id;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    List<ReportFileResponse> childList;

    public ReportResponse(Report report) {
        this.id = report.getId();
        this.hit = report.getHit();
        this.author = report.getAuthor();
        this.title = report.getTitle();
        this.content = report.getContent();
        this.contentVal = report.getContentVal();
        this.regDt = report.getRegDt();
        this.childList = report.getChildList() != null
                ? report.getChildList().stream().map(reportFile -> new ReportFileResponse(reportFile))
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Data
    public static class ReportFileResponse {

        private Long id;

        private Report report;

        private Long fileId;

        public ReportFileResponse(ReportFile reportFile) {
            this.id = reportFile.getId();
            this.report = reportFile.getReport();
            this.fileId = reportFile.getFileId();
        }
    }
}
