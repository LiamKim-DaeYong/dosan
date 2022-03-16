package com.samin.dosan.domain.homepage.notice.dto;

import com.samin.dosan.domain.homepage.notice.Homepage_Notice;
import com.samin.dosan.domain.homepage.notice.file.NoticeFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NoticeResponse {

    private Long id;

    private int hit;

    private String author;

    private String title;

    private String content;

    private String contentVal;

    private LocalDate regDt;

    List<NoticeFileResponse> childList;

    public NoticeResponse(Homepage_Notice notice) {
        this.id = notice.getId();
        this.hit = notice.getHit();
        this.author = notice.getAuthor();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.contentVal = notice.getContentVal();
        this.regDt = notice.getRegDt();
        this.childList = notice.getChildList() != null
                ? notice.getChildList().stream().map(noticeFile -> new NoticeFileResponse(noticeFile))
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Data
    public static class NoticeFileResponse {
        private Long id;

        private Homepage_Notice notice;

        private Long fileId;

        private String isFile;

        public NoticeFileResponse(NoticeFile noticeFile) {
            this.id = noticeFile.getId();
            this.notice = noticeFile.getNotice();
            this.fileId = noticeFile.getFileId();
            this.isFile = noticeFile.getIsFile();
        }
    }
}
