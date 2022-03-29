package com.samin.dosan.domain.homepage.popup;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.common.SingleFile;
import com.samin.dosan.domain.homepage.type.DateSetType;
import com.samin.dosan.domain.homepage.type.PostType;
import com.samin.dosan.web.dto.homepage.popup.PopupSave;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "homepage_popup")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Popup extends SingleFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private PostType postYn;

    @Column(length = 1, nullable = false)
    private DateSetType dateSet;

    @Column(length = 20, nullable = false)
    private String postStart;

    @Column(length = 20, nullable = false)
    private String postEnd;

    @Column(nullable = false)
    private String title;

    @Column
    private String link;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Popup of(PopupSave saveData) {
        Popup popup = new Popup();
        popup.regDt = LocalDate.now();
        popup.used = Used.Y;

        popup.title = saveData.getTitle();
        popup.link = saveData.getLink();
        popup.postYn = saveData.getPostYn();
        popup.dateSet = saveData.getDateSet();
        popup.postStart = saveData.getPostStart();
        popup.postEnd = saveData.getPostEnd();

        return popup;
    }

    public void addFile(UploadFile file) {
        this.originFileName = file.getOriginalFilename();
        this.storeFileName = file.getStoreFileName();
        this.contentType = file.getContentType();
        this.extension = file.getExtension();
        this.fileSize = file.getFileSize();
    }

    public void update(PopupSave updateData) {
        this.title = updateData.getTitle();
        this.link = updateData.getLink();
        this.postYn = updateData.getPostYn();
        this.dateSet = updateData.getDateSet();
        this.postStart = updateData.getPostStart();
        this.postEnd = updateData.getPostEnd();

        updateFile(updateData.getFiles());
    }

    private void updateFile(MultipartFile file) {
        try {
            FileUtils.deleteFile(this.storeFileName);

            UploadFile uploadFile = FileUtils.fileUpload(file);
            addFile(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        this.used = Used.N;
    }
}
