package com.samin.dosan.domain.notice;

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
public class NoticeFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    /*================== Business Logic ==================*/
    public static NoticeFile of(UploadFile uploadFile) {
        NoticeFile noticeFile = new NoticeFile();
        noticeFile.originFilename = uploadFile.getOriginalFilename();
        noticeFile.storeFileName = uploadFile.getStoreFileName();
        noticeFile.contentType = uploadFile.getContentType();
        noticeFile.extension = uploadFile.getExtension();
        noticeFile.fileSize = uploadFile.getFileSize();

        return noticeFile;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
