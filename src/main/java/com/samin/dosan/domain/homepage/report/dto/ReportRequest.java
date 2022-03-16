package com.samin.dosan.domain.homepage.report.dto;

import com.samin.dosan.domain.homepage.report.Report;
import com.samin.dosan.domain.homepage.report.file.ReportFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReportRequest {

    private Long id;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private List<Long> imgIdList;

    public Report toEntity() {
        Report report = Report.builder()
                .id(this.id)
                .hit(this.hit)
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .contentVal(this.contentVal)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .childList(new ArrayList<>())
                .build();

        if (content != null) {
            report.addContentSystem(report.getContent());
        }

        if (imgIdList != null) {
            imgIdList.forEach(fileId -> {
                ReportFileRequest request = new ReportFileRequest();
                request.setFileId(fileId);

                report.addChild(request.toEntity());
            });
        }

        return report;
    }

    @Data
    public static class ReportFileRequest {

        private Long id;

        private Report report;

        private Long fileId;

        private LocalDate regDt;

        public ReportFile toEntity() {
            return ReportFile.builder()
                    .id(this.id)
                    .report(this.report)
                    .fileId(this.fileId)
                    .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                    .build();
        }
    }
}
