package com.samin.dosan.domain.homepage.newsletter.dto;

import com.samin.dosan.domain.homepage.newsletter.Newsletter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsletterRequest {

    private Long id;

    private Long fileId;

    private Long previewId;

    private int hit;

    private String title;

    private LocalDate regDt;

    public Newsletter toEntity() {
        return Newsletter.builder()
                .id(this.id)
                .fileId(this.fileId)
                .previewId(this.previewId)
                .hit(this.hit)
                .title(this.title)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .build();
    }
}
