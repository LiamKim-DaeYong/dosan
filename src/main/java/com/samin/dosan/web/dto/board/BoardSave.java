package com.samin.dosan.web.dto.board;

import com.samin.dosan.domain.board.Board;
import lombok.Data;

import java.util.ArrayList;

@Data
public class BoardSave {

    private Long id;

    private String title;

    private String content;

    private String writer;

    public Board toEntity() {
        return Board.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .comments(new ArrayList<>())
                .files(new ArrayList<>())
                .build();
    }
}
