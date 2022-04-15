package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.file.Files;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "homepage_board_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomepageBoardFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private HomepageBoard board;

    //================== 연관 관계 메서드 ==================//
    public void setBoard(HomepageBoard board) {
        this.board = board;
    }

    //==================   생성 메서드   ==================//
    public static HomepageBoardFile of(UploadFile uploadFile) {
        HomepageBoardFile boardFile = new HomepageBoardFile();
        boardFile.originFileName = uploadFile.getOriginalFileName();
        boardFile.storeFileName = uploadFile.getStoreFileName();
        boardFile.contentType = uploadFile.getContentType();
        boardFile.extension = uploadFile.getExtension();
        boardFile.fileSize = uploadFile.getFileSize();

        return boardFile;
    }
    //==================  비즈니스 로직  ==================//

    //==================   조회 메서드   ==================//

}
