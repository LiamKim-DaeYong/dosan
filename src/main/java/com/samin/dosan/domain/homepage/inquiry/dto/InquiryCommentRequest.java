package com.samin.dosan.domain.homepage.inquiry.dto;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InquiryCommentRequest {

    private Long id;

    private Inquiry inquiry;

    private String author;

    private String comment;

    private LocalDate regDt;

    public InquiryComment toEntity() {
        return InquiryComment.builder()
                .id(this.id)
                .inquiry(this.inquiry)
                .author(author)
                .comment(comment)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .build();
    }
}
