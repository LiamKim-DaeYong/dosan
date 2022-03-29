package com.samin.dosan.domain.homepage.media_archive.webtoon;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.file.FileUtils;
import com.samin.dosan.core.utils.file.UploadFile;
import com.samin.dosan.web.dto.homepage.webtoon.WebtoonSave;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "homepage_webtoon")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon extends WebtoonPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Webtoon of(WebtoonSave saveData) {
        Webtoon webtoon = new Webtoon();
        webtoon.regDt = LocalDate.now();
        webtoon.used = Used.Y;
        webtoon.author = "도산서원 선비문화수련원";

        webtoon.title = saveData.getTitle();

        return webtoon;
    }

    public void addPdf(UploadFile file) {
        this.originPdfName = file.getOriginalFilename();
        this.storePdfName = file.getStoreFileName();
        this.pdfContentType = file.getContentType();
        this.pdfExtension = file.getExtension();
        this.pdfSize = file.getFileSize();
    }

    public void addImg(UploadFile file) {
        this.originFileName = file.getOriginalFilename();
        this.storeFileName = file.getStoreFileName();
        this.contentType = file.getContentType();
        this.extension = file.getExtension();
        this.fileSize = file.getFileSize();
    }

    public void update(WebtoonSave updateData) {
        this.title = updateData.getTitle();

        updateFile(updateData.getPdf(), "pdf");
        updateFile(updateData.getImg(), "img");

    }

    private void updateFile(MultipartFile file, String type) {
        if (!file.getOriginalFilename().isBlank()) {
            try {
                FileUtils.deleteFile(this.storePdfName);

                UploadFile uploadPdf = FileUtils.fileUpload(file);

                if (type.equals("pdf")) {
                    addPdf(uploadPdf);
                } else {
                    addImg(uploadPdf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        this.used = Used.N;
    }
}
