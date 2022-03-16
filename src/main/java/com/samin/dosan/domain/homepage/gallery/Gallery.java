package com.samin.dosan.domain.homepage.gallery;

import com.samin.dosan.domain.homepage.gallery.file.GalleryFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name="homepage_gallery")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long previewId;

    @Column(nullable = false)
    private int hit;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(columnDefinition="TEXT")
    private String contentVal;

    @Column(columnDefinition="TEXT", nullable = false)
    private String contentSystem;

    @Column(nullable = false)
    private LocalDate regDt;

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GalleryFile> childList;

    public void addContentSystem(String content) {
        String contentSystem = content;
        if (content.contains("/admin/homepage/file/preview")) {
            contentSystem = contentSystem.replace("/admin/homepage/file/preview", "/file/preview");
        }

        this.contentSystem = contentSystem;
    }

    public void addChild(GalleryFile galleryFile) {
        childList.add(galleryFile);
        galleryFile.setParent(this);
    }

    public void setPreviewId(Long previewId) {
        this.previewId = previewId;
    }
}
