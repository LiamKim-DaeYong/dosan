package com.samin.dosan.domain.homepage.main_image;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.PostType;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.web.dto.homepage.mainImage.MainImageSave;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Table(name = "homepage_main_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainImage extends BaseEntity {

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
    private String originFilename;

    @Column(nullable = false)
    private String storeFileName;

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
        mainImage.originFilename = saveData.getFile().getOriginalFilename();
        mainImage.storeFileName = UUID.randomUUID()+"_"+mainImage.originFilename;

        return mainImage;
    }

    public void update(MainImageSave updateData) {
        this.title = updateData.getTitle();
        this.postYn = updateData.getPostYn();
        this.sort = updateData.getSort();
        this.originFilename = updateData.getFile().getOriginalFilename();
        this.storeFileName = UUID.randomUUID()+"_"+this.originFilename;
    }

    public void delete() {
        this.used = Used.N;
    }
}
