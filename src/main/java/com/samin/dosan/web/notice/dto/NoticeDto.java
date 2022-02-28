package com.samin.dosan.web.notice.dto;

import com.samin.dosan.domain.notice.Notice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class NoticeDto {

    private Long id;

    private String title;

    private Integer commentCnt;

    private String writer;

    private LocalDate registDt;

    public NoticeDto(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.commentCnt = notice.getCommentsList().size();
        this.writer = notice.getCreatedBy();
        this.registDt = notice.getCreatedAt().toLocalDate();
    }
}
