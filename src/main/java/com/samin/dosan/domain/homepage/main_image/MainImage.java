package com.samin.dosan.domain.homepage.main_image;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.domain.homepage.common.SingleFile;
import com.samin.dosan.domain.homepage.type.PostType;
import com.samin.dosan.web.dto.homepage.mainImage.MainImageSave;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "homepage_main_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainImage extends SingleFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private PostType postYn;

    @Column(nullable = false)
    private Integer sort;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static MainImage of(MainImageSave saveData) {
        MainImage mainImage = new MainImage();
        mainImage.regDt = LocalDate.now();
        mainImage.used = Used.Y;

        mainImage.title = saveData.getTitle();
        mainImage.postYn = saveData.getPostYn();
        mainImage.sort = saveData.getSort();

        return mainImage;
    }

    public void addFile(UploadFile file) {
        this.originFileName = file.getOriginalFilename();
        this.storeFileName = file.getStoreFileName();
        this.contentType = file.getContentType();
        this.extension = file.getExtension();
        this.fileSize = file.getFileSize();
    }

    public void update(MainImageSave updateData) {
        this.title = updateData.getTitle();
        this.postYn = updateData.getPostYn();
        this.sort = updateData.getSort();

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
