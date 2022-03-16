package com.samin.dosan.domain.homepage.popup.dto;

import com.samin.dosan.domain.homepage.popup.Popup;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PopupChangePostYnRequest {

    private Long id;

    private Long fileId;

    private String postYn;

    private String dateSet;

    private String postStart;

    private String postEnd;

    private String title;

    private String link;

    private LocalDateTime regDt;

    public PopupChangePostYnRequest(Popup popup, String postYn) {
        this.id = popup.getId();
        this.fileId = popup.getFileId();
        this.postYn = postYn;
        this.dateSet = popup.getDateSet();
        this.postStart = popup.getPostStart();
        this.postEnd = popup.getPostEnd();
        this.title = popup.getTitle();
        this.link = popup.getLink();
        this.regDt = popup.getRegDt();
    }

    public Popup toEntity() {
        return Popup.builder()
                .id(this.id)
                .fileId(this.fileId)
                .postYn(this.postYn)
                .dateSet(this.dateSet)
                .postStart(this.postStart)
                .postEnd(this.postEnd)
                .title(this.title)
                .link(this.link)
                .regDt(this.regDt)
                .build();
    }
}
