package com.samin.dosan.domain.homepage.inquiry.dto;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import com.samin.dosan.domain.homepage.inquiry.file.InquiryFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InquiryRequest {

    private Long id;

    private int hit;

    private String secret;

    private String password;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    private List<Long> imgIdList;

    private List<Long> fileIdList;

    public Inquiry toEntity() {
        Inquiry inquiry = Inquiry.builder()
                .id(this.id)
                .hit(this.hit)
                .secret(this.secret != null ? this.secret : "N")
                .password(this.secret.equals("Y") ? this.password : null)
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .contentVal(this.contentVal)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .inquiryFileList(new ArrayList<>())
                .build();


        if (imgIdList != null) {
            imgIdList.forEach(fileId -> {
                InquiryFileRequest request = new InquiryFileRequest();
                request.setFileId(fileId);

                inquiry.addInquiryFile(request.toEntity("N"));
            });
        }

        if (fileIdList != null) {
            fileIdList.forEach(fileId -> {
                InquiryFileRequest request = new InquiryFileRequest();
                request.setFileId(fileId);

                inquiry.addInquiryFile(request.toEntity("Y"));
            });
        }

        return inquiry;
    }

    @Data
    public static class InquiryFileRequest {

        private Long id;

        private Inquiry inquiry;

        private Long fileId;

        private String isFile;

        private LocalDate regDt;

        public InquiryFile toEntity(String isFile) {
            return InquiryFile.builder()
                    .id(this.id)
                    .inquiry(this.inquiry)
                    .fileId(this.fileId)
                    .isFile(isFile)
                    .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                    .build();
        }
    }
}
