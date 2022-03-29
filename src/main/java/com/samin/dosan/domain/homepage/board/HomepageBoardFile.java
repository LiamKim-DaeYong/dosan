package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.file.Files;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomepageBoardFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private HomepageBoard board;

    /*================== Business Logic ==================*/
    public static HomepageBoardFile of(UploadFile uploadFile) {
        HomepageBoardFile boardFile = new HomepageBoardFile();
        boardFile.originFilename = uploadFile.getOriginalFilename();
        boardFile.storeFileName = uploadFile.getStoreFileName();
        boardFile.contentType = uploadFile.getContentType();
        boardFile.extension = uploadFile.getExtension();
        boardFile.fileSize = uploadFile.getFileSize();

        return boardFile;
    }

    public void setBoard(HomepageBoard board) {
        this.board = board;
    }
}
