package com.samin.dosan.domain.homepage.mainImage.dto;

import com.samin.dosan.domain.homepage.mainImage.MainImage;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MainImageChangePostRequest {
    private Long id;

    private Long fileId;

    private String postYn;

    private int postSeq;

    private String title;

    private LocalDate regDt;

    public MainImageChangePostRequest(MainImage mainImage) {
        this.id = mainImage.getId();
        this.fileId = mainImage.getFileId();
        this.postYn = mainImage.getPostYn();
        this.postSeq = mainImage.getPostSeq();
        this.title = mainImage.getTitle();
        this.regDt = mainImage.getRegDt();
    }

    public MainImage toEntity(String postYn, int postSeq) {
        return MainImage.builder()
                .id(this.id)
                .fileId(this.fileId)
                .postYn(postYn)
                .postSeq(postSeq)
                .title(this.title)
                .regDt(this.regDt)
                .build();
    }
}
