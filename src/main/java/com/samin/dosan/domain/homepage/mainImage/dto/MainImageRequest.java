package com.samin.dosan.domain.homepage.mainImage.dto;

import com.samin.dosan.domain.homepage.mainImage.MainImage;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MainImageRequest {

    private Long id;

    private Long fileId;

    private String postYn;

    private int postSeq;

    private String title;

    private LocalDate regDt;

    public MainImage toEntity() {
        return MainImage.builder()
                .id(this.id)
                .fileId(this.fileId)
                .postYn(this.postYn)
                .postSeq(this.postSeq)
                .title(this.title)
                .regDt(this.regDt == null ? LocalDate.now() : this.regDt)
                .build();
    }
}
