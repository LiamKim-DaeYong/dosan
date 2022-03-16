package com.samin.dosan.domain.homepage.mainImage.dto;

import com.samin.dosan.domain.homepage.mainImage.MainImage;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MainImageChangeSeqRequest {

    private Long id;

    private Long fileId;

    private String postYn;

    private int postSeq;

    private String title;

    private LocalDate regDt;

    public MainImageChangeSeqRequest(MainImage mainImage, int seq) {
        this.id = mainImage.getId();
        this.fileId = mainImage.getFileId();
        this.postYn = mainImage.getPostYn();
        this.postSeq = seq;
        this.title = mainImage.getTitle();
        this.regDt = mainImage.getRegDt();
    }

    public MainImage toEntity() {
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
