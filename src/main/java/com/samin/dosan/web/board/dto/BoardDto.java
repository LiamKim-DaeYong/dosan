package com.samin.dosan.web.board.dto;

import com.samin.dosan.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class BoardDto {

    private Long id;

    private String title;

    private Integer commentCnt;

    private String writer;

    private LocalDate registDt;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.commentCnt = board.getCommentsList().size();
        this.writer = board.getCreatedBy();
        this.registDt = board.getCreatedAt().toLocalDate();
    }
}
