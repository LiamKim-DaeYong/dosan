package com.samin.dosan.domain.board;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.board.comment.Comments;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String writer;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "boardId")
    private List<BoardFile> files = new ArrayList<>();

    public void updateBoard(Board board) {
        this.title = board.title;
        this.content = board.getContent();
    }

    public void addFiles(List<BoardFile> newFiles) {
        this.files.addAll(newFiles);
    }

    public void delFiles(List<BoardFile> willDeleteFiles) {
        willDeleteFiles.stream().forEach(f -> {
            this.files.removeIf(file -> file.getId().equals(f.getId()));
        });
    }
}
