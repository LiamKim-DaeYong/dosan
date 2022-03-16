package com.samin.dosan.domain.homepage.popup.dto;

import com.samin.dosan.domain.homepage.popup.Popup;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PopupRequest {

    private Long id;

    private Long fileId;

    private String postYn;

    private String dateSet;

    private String postStart;

    private String postEnd;

    private String title;

    private String link;

    private LocalDateTime regDt;

    public Popup toEntity() {
        if (this.link.equals("")) {
            this.link = "javascript:void(0)";
        }

        return Popup.builder()
                .id(this.id)
                .fileId(this.fileId)
                .postYn(this.postYn)
                .dateSet(this.dateSet)
                .postStart(this.postStart)
                .postEnd(this.postEnd)
                .title(this.title)
                .link(this.link)
                .regDt(this.regDt == null ? LocalDateTime.now() : this.regDt)
                .build();
    }
}
