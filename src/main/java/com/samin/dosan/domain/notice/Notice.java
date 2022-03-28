package com.samin.dosan.domain.notice;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.notice.comment.NoticeComments;
import com.samin.dosan.web.dto.notice.NoticeSave;
import com.samin.dosan.web.dto.notice.NoticeUpdate;
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
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String writer;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeComments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFile> files = new ArrayList<>();

    /*================== Business Logic ==================*/
    public static Notice of(NoticeSave saveData, String userNm) {
        Notice notice = new Notice();
        notice.title = saveData.getTitle();
        notice.content = saveData.getContent();
        notice.writer = userNm;

        return notice;
    }

    public void updateNotice(Notice notice) {
        this.title = notice.title;
        this.content = notice.content;
    }

    public void addFile(UploadFile file) {
        NoticeFile noticeFile = NoticeFile.of(file);

        noticeFile.setNotice(this);
        this.files.add(noticeFile);
    }

    public void update(NoticeUpdate updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();

        updateFiles(updateData.getSavedFileIds(), updateData.getFiles());
    }

    private void updateFiles(List<Long> savedFileIds, List<MultipartFile> updateFiles) {
        List<NoticeFile> resultList = new ArrayList<>();

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
                NoticeFile noticeFile = NoticeFile.of(uploadFile);
                noticeFile.setNotice(this);

                resultList.add(noticeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.files.clear();
        this.files.addAll(resultList);
    }
}
