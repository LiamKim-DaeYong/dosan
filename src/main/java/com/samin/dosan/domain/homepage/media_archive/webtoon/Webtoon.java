package com.samin.dosan.domain.homepage.media_archive.webtoon;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.web.dto.homepage.webtoon.WebtoonSave;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "homepage_webtoon")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String pdfOriginFilename;

    @Column(nullable = false)
    private String pdfStoreFileName;

    @Column(nullable = false)
    private String imgOriginFilename;

    @Column(nullable = false)
    private String imgStoreFileName;

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

        webtoon.pdfOriginFilename = saveData.getPdf().getOriginalFilename();
        webtoon.pdfStoreFileName = UUID.randomUUID()+"_"+webtoon.pdfOriginFilename;
        webtoon.imgOriginFilename = saveData.getImg().getOriginalFilename();
        webtoon.imgStoreFileName = UUID.randomUUID()+"_"+webtoon.imgOriginFilename;

        return webtoon;
    }

    public void update(WebtoonSave updateData) {
        this.title = updateData.getTitle();

        this.pdfOriginFilename = updateData.getPdf().getOriginalFilename();
        this.pdfStoreFileName = UUID.randomUUID()+"_"+this.pdfOriginFilename;
        this.imgOriginFilename = updateData.getImg().getOriginalFilename();
        this.imgStoreFileName = UUID.randomUUID()+"_"+this.imgStoreFileName;
    }

    public void delete() {
        this.used = Used.N;
    }
}
