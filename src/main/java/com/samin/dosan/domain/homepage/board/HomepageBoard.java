package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.type.BoardType;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardSave;
import com.samin.dosan.web.dto.homepage.board.HomepageBoardUpdate;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@Builder
@Table(name = "homepage_board")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomepageBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Column(nullable = false)
    private Integer hit;

    @Column(length = 100, nullable = false)
    private String author;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HomepageBoardFile> files = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static HomepageBoard of(HomepageBoardSave saveData, BoardType boardType) {
        HomepageBoard board = new HomepageBoard();
        board.hit = 0;
        board.author = "도산서원 선비문화수련원";
        board.regDt = LocalDate.now();
        board.used = Used.Y;

        board.title = saveData.getTitle();
        board.content = saveData.getContent();
        board.boardType = boardType;

        return board;
    }

    public void addFile(UploadFile file) {
        HomepageBoardFile boardFile = HomepageBoardFile.of(file);

        boardFile.setBoard(this);
        this.files.add(boardFile);
    }

    public void update(HomepageBoardUpdate updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();

        updateFiles(updateData.getSavedFileIds(), updateData.getFiles());
    }

    private void updateFiles(List<Long> savedFileIds, List<MultipartFile> updateFiles) {
        List<HomepageBoardFile> resultList=  new ArrayList<>();

        this.files.stream().forEach(file -> {
            Optional<Long> savedFileId = savedFileIds.stream()
                    .filter(ids -> file.getId().equals(ids)).findAny();

            if (savedFileId.isEmpty()) {
                FileUtils.deleteFile(file.getStoreFileName());
            } else {
                resultList.add(file);
            }
        });

        updateFiles.stream().forEach(updateFile -> {
            try {
                UploadFile uploadFile = FileUtils.fileUpload(updateFile);
                HomepageBoardFile boardFile = HomepageBoardFile.of(uploadFile);
                boardFile.setBoard(this);

                resultList.add(boardFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.files.clear();
        this.files.addAll(resultList);
    }

    public void delete() {
        this.used = Used.N;
    }
}
