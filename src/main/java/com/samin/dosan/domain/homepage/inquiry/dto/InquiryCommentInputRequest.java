package com.samin.dosan.domain.homepage.inquiry.dto;

import com.samin.dosan.domain.homepage.inquiry.Inquiry;
import com.samin.dosan.domain.homepage.inquiry.comment.InquiryComment;
import lombok.Data;

@Data
public class InquiryCommentInputRequest {

    private Long inquiryId;

    private Long commentId;

    private String author;

    private String comment;

    public Inquiry toEntity(Inquiry inquiry, InquiryComment inquiryComment) {
        inquiry = Inquiry.builder()
                .id(inquiry.getId())
                .hit(inquiry.getHit())
                .secret(inquiry.getSecret())
                .password(inquiry.getPassword())
                .author(inquiry.getAuthor())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .contentVal(inquiry.getContentVal())
                .contentSystem(inquiry.getContentSystem())
                .regDt(inquiry.getRegDt())
                .inquiryFileList(inquiry.getInquiryFileList())
                .inquiryComment(new InquiryComment())
                .build();

        if (inquiryComment != null) {
            inquiry.addInquiryComment(inquiryComment);
        }

        return inquiry;
    }
}
