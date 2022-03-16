package com.samin.dosan.domain.homepage.mainImage.dto;

import com.samin.dosan.domain.homepage.mainImage.MainImage;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MainImageResponse {

    private Long id;

    private Long fileId;

    private String postYn;

    private int postSeq;

    private String title;

    private LocalDate regDt;

    public MainImageResponse(MainImage mainImage) {
        this.id = mainImage.getId();
        this.fileId = mainImage.getFileId();
        this.postYn = mainImage.getPostYn();
        this.postSeq = mainImage.getPostSeq();
        this.title = mainImage.getTitle();
        this.regDt = mainImage.getRegDt();
    }
}
