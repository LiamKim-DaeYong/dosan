package com.samin.dosan.domain.homepage.webtoon.dto;

import com.samin.dosan.domain.homepage.webtoon.Webtoon;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WebtoonRequest {

    private Long id;

    private Long pdfId;

    private Long previewId;

    private String author;

    private String title;

    private LocalDate regDt;

    public Webtoon toEntity() {
        return Webtoon.builder()
                .id(this.id)
                .pdfId(this.pdfId)
                .previewId(this.previewId)
                .author(this.author)
                .title(this.title)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .build();
    }
}
