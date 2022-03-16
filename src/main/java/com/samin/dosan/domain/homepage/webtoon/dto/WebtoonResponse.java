package com.samin.dosan.domain.homepage.webtoon.dto;

import com.samin.dosan.domain.homepage.webtoon.Webtoon;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WebtoonResponse {

    private Long id;

    private Long pdfId;

    private Long previewId;

    private String author;

    private String title;

    private LocalDate regDt;

    public WebtoonResponse(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.pdfId = webtoon.getPdfId();
        this.previewId = webtoon.getPreviewId();
        this.author = webtoon.getAuthor();
        this.title = webtoon.getTitle();
        this.regDt = webtoon.getRegDt();
    }
}
