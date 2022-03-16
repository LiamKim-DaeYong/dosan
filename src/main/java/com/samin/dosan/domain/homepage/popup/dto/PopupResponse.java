package com.samin.dosan.domain.homepage.popup.dto;

import com.samin.dosan.domain.homepage.popup.Popup;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PopupResponse {

    private Long id;

    private Long fileId;

    private String postYn;

    private String dateSet;

    private String postStart;

    private String postEnd;

    private String title;

    private String link;

    private LocalDateTime regDt;

    public PopupResponse(Popup popup) {
        this.id = popup.getId();
        this.fileId = popup.getFileId();
        this.postYn = popup.getPostYn();
        this.dateSet = popup.getDateSet();
        this.postStart = popup.getPostStart();
        this.postEnd = popup.getPostEnd();
        this.title = popup.getTitle();
        this.link = popup.getLink();
        this.regDt = popup.getRegDt();
    }
}
