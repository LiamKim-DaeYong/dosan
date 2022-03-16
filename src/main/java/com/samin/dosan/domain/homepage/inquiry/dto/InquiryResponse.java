package com.samin.dosan.domain.homepage.inquiry.dto;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import com.samin.dosan.domain.homepage.inquiry.file.InquiryFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class InquiryResponse {

    private Long id;

    private int hit;

    private String secret;

    private String password;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private String contentSystem;

    private LocalDate regDt;

    private List<InquiryFileResponse> inquiryFileList;

    private InquiryCommentResponse inquiryComment;


    public InquiryResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.hit = inquiry.getHit();
        this.secret = inquiry.getSecret();
        this.password = inquiry.getPassword();
        this.author = inquiry.getAuthor();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.contentVal = inquiry.getContentVal();
        this.contentSystem = inquiry.getContentSystem();
        this.regDt = inquiry.getRegDt();
        this.inquiryComment = inquiry.getInquiryComment() != null
                ? new InquiryCommentResponse(inquiry.getInquiryComment())
                : null;

        this.inquiryFileList = inquiry.getInquiryFileList() != null
                ? inquiry.getInquiryFileList().stream().map(inquiryFile -> new InquiryFileResponse(inquiryFile))
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Data
    public static class InquiryFileResponse {
        private Long id;

        private Long fileId;

        private String isFile;

        public InquiryFileResponse(InquiryFile inquiryFile) {
            this.id = inquiryFile.getId();
            this.fileId = inquiryFile.getFileId();
            this.isFile = inquiryFile.getIsFile();
        }
    }

    @Data
    public static class InquiryCommentResponse {
        private Long id;

        private String author;

        private String comment;

        public InquiryCommentResponse(InquiryComment inquiryComment) {
            this.id = inquiryComment.getId();
            this.author = inquiryComment.getAuthor();
            this.comment = inquiryComment.getComment();
        }
    }
}
