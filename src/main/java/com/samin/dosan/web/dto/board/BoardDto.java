package com.samin.dosan.web.dto.board;

import com.samin.dosan.domain.board.Board;
import lombok.Data;

public class BoardDto {

    @Data
    public static class Request {

        private Long id;

        private String title;

        private String content;

        private String writer;

//        public Board toEntity() {
//            return Board.builder()
//                    .id(this.id)
//                    .title(this.title)
//                    .content(this.content)
//                    .writer(this.writer)
//                    .build();
//        }
    }

    @Data
    public static class Attachment {

        private Long id;

        private String filename;

        private String url;
    }
}
