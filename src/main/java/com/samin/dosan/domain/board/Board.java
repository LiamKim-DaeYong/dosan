package com.samin.dosan.domain.board;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.board.comment.Comments;
import com.samin.dosan.web.dto.board.BoardSave;
import com.samin.dosan.web.dto.board.BoardUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardFile> files = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static Board of(BoardSave saveData, String userNm) {
        Board board = new Board();
        board.title = saveData.getTitle();
        board.content = saveData.getContent();
        board.writer = userNm;

        return board;
    }

    public void addFile(UploadFile file) {
        BoardFile boardFile = BoardFile.of(file);

        boardFile.setBoard(this);
        this.files.add(boardFile);
    }

    public void update(BoardUpdate updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();

        updateFiles(updateData.getSavedFileIds(), updateData.getFiles());
    }

    private void updateFiles(List<Long> savedFileIds, List<MultipartFile> updateFiles) {
        List<BoardFile> resultList = new ArrayList<>();

        this.files.stream().forEach(file -> {
            Optional<Long> savedFileId = savedFileIds.stream().filter(ids -> file.getId().equals(ids)).findAny();

            if (savedFileId.isEmpty()) {
                FileUtils.deleteFile(file.getStoreFileName());
            } else {
                resultList.add(file);
            }
        });

        updateFiles.stream().forEach(updateFile -> {
            try {
                UploadFile uploadFile = FileUtils.fileUpload(updateFile);
                BoardFile boardFile = BoardFile.of(uploadFile);
                boardFile.setBoard(this);

                resultList.add(boardFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.files.clear();
        this.files.addAll(resultList);
    }
}
