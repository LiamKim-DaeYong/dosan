package com.samin.dosan.domain.board;

import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.file.Files;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BOARD")
@NoArgsConstructor
public class BoardFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    /*================== Business Logic ==================*/
    public static BoardFile of(UploadFile uploadFile) {
        BoardFile boardFile = new BoardFile();
        boardFile.originFilename = uploadFile.getOriginalFilename();
        boardFile.storeFileName = uploadFile.getStoreFileName();
        boardFile.contentType = uploadFile.getContentType();
        boardFile.extension = uploadFile.getExtension();
        boardFile.fileSize = uploadFile.getFileSize();

        return boardFile;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

//    public static BoardFile toEntity(FileDto fileDto) {
//
//        // 확장자 (마지막 닷(.) 이후 문자열)
//        Function<String, String> getExtension = (originalName) -> {
//            int findDot = originalName.lastIndexOf(".");
//            return (findDot != -1) ? originalName.substring(findDot) : "";
//        };
//
//        BoardFile files = new BoardFile();
//        files.id = fileDto.getId();
//        files.originFilename = fileDto.getOriginalName();
//        files.storeFileName = fileDto.getStorageName();
//        files.contentType = fileDto.getContentType();
//        files.extension = getExtension.apply(fileDto.getOriginalName());
//        files.fileSize = fileDto.getByteSize();
//        return files;
//    }
}
