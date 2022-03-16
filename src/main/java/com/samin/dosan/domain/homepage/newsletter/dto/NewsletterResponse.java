package com.samin.dosan.domain.homepage.newsletter.dto;

import com.samin.dosan.domain.homepage.newsletter.Newsletter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsletterResponse {

    private Long id;

    private Long fileId;

    private Long previewId;

    private int hit;

    private String title;

    private LocalDate regDt;

    public NewsletterResponse(Newsletter newsletter) {
        this.id = newsletter.getId();
        this.fileId = newsletter.getFileId();
        this.previewId = newsletter.getPreviewId();
        this.hit = newsletter.getHit();
        this.title = newsletter.getTitle();
        this.regDt = newsletter.getRegDt();
    }
}
